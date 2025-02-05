package org.uni2growcm.camel;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto-detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {
    //SimpleDateFormat dateFormat = new SimpleDateFormat("aaaa-MM-dd");

    @Autowired
    DataSource dataSource;

    @Override
    public void configure() {
        //restConfiguration().host("localhost:3006").producerComponent("http");
        restConfiguration()
                .host("localhost")
                .port(3006)
                .bindingMode(RestBindingMode.json);
        /*
        from("timer:hello?period={{timer.period}}").routeId("hello")
                .transform().method("myBean", "saySomething")
                    .end()
                //.to("stream:out");
                .to("rest:post:sink?outType=org.uni2growcm.camel.MyResponse").log("${body}");
        */
        from("timer://etl?period=5000")
                .setBody(simple("Laptop,Mouse,Keyboard"))
                .split(body().tokenize(","))
                .to("seda:jobQueue?multipleConsumers=true");

        from("seda:jobQueue?multipleConsumers=true")
                .routeId("jobQueue")
                .to("direct:job");

        from("direct:job")
                .filter(simple("${body} == 'Laptop'"))
                    .setHeader("prod", constant("Laptop"))
                    .setBody(constant("SELECT p.name, p.price AS unit_price, s.quantity, s.quantity * p.price AS total_price, s.sale_date\n" +
                            "FROM sales s\n" +
                            "JOIN products p ON s.product_id = p.id\n" +
                            "WHERE p.name = :?prod"))
                    .to("jdbc:dataSource?useHeadersAsParameters=true&outputClass=org.uni2growcm.camel.SaleOutput")
                    .process(new MyCustomProcessor())
                        .to("seda:restQueue?multipleConsumers=true").end()
                .filter(simple("${body} == 'Mouse'"))
                    .setHeader("prod", constant("Mouse"))
                    .setBody(constant("SELECT p.name, p.price AS unit_price, s.quantity, s.quantity * p.price AS total_price, s.sale_date\n" +
                            "FROM sales s\n" +
                            "JOIN products p ON s.product_id = p.id\n" +
                            "WHERE p.name = :?prod"))
                    .to("jdbc:dataSource?useHeadersAsParameters=true&outputClass=org.uni2growcm.camel.SaleOutput")
                    .process(new MyCustomProcessor())
                        .to("seda:restQueue?multipleConsumers=true").end()
                .filter(simple("${body} == 'Keyboard'"))
                    .setHeader("prod", constant("Keyboard"))
                    .setBody(constant("SELECT p.name, p.price AS unit_price, s.quantity, s.quantity * p.price AS total_price, s.sale_date\n" +
                            "FROM sales s\n" +
                            "JOIN products p ON s.product_id = p.id\n" +
                            "WHERE p.name = :?prod"))
                    .to("jdbc:dataSource?useHeadersAsParameters=true&outputClass=org.uni2growcm.camel.SaleOutput")
                    .process(new MyCustomProcessor())
                            .to("seda:restQueue?multipleConsumers=true");

        Predicate hasSaleItemsInline = exchange -> {
            MyMessage myMessage = exchange.getMessage().getBody(MyMessage.class); // Get the body as your Order class
            return myMessage != null && myMessage.getSaleItems() != null && !myMessage.getSaleItems().isEmpty();
        };

        from("seda:restQueue?multipleConsumers=true")
                .routeId("restQueue")
                .filter(hasSaleItemsInline)
                    .marshal().json()
                    .end()
                    .to("rest:post:sink?outType=org.uni2growcm.camel.MyResponse").log("Response -> ${body}");

    }

}
