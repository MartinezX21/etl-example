const express = require('express');

const app = express ();
app.use(express.json());

app.post('/sink', (request, response) => {
    console.log(request.body);
    const status = {
        status: "Ok",
        message: "Your request is being processed."
    };
    response.send(status);
});

const PORT = process.env.PORT || 3006;
app.listen(PORT, () => {
    console.log("Server Listening on PORT:", PORT);
});