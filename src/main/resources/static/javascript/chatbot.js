let AIResponse = "Hi, I am your Travel Assistant. How may I help you?";
let userQuestion = "";

$(document).ready(function () {
    const popover = $('.example-popover');
    // Initialize the popover
    popover.popover({
        html: true, // Allow HTML content in the popover
        placement: 'left',
        content: `<div class='popover-content'></div>`,
        trigger: 'click',
        boundary: 'window',
        offset: '15, 15'
    });

    popover.on('shown.bs.popover', function () {
        const content = $(".popover-content");
        content.append(
            `<div class="container">
                    <div class="col-12">
                        <h5>Travel Assistant</h5>
                    </div>
            </div>`
        )

        content.append(`<div id="answerBox"><p>${AIResponse}</p></div>`);

        content.append(`<textarea class="form-control" id="chatbot-input" rows="3" placeholder="Type your message here...">${userQuestion}</textarea>`);

        content.append('<div><button type="button" class="btn btn-primary" id="btn-chatbot">Send</button></div>');

        $('#chatbot-input').on('change', function () {
            userQuestion = $(this).val();
        });

        $("#btn-chatbot").click(function () {
            let userMessage = $("#chatbot-input").val();
            $("#chatbot-input").val("");
            userQuestion = "";

            $("#answerBox").html(`
                <button class="btn btn-primary" type="button" disabled>
                    <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                    Please wait. Working on it ...
                </button>
<!--                <div class="spinner-border spinner-border-sm" role="status">-->
<!--                    <span class="sr-only">Loading...</span>-->
<!--                </div>-->
<!--                <div class="spinner-grow spinner-grow-sm" role="status">-->
<!--                    <span class="sr-only">Loading...</span>-->
<!--                </div>-->
            `);

            const token = localStorage.getItem("token") ?? "";

            $.ajax({
                url: "/chat",
                type: "POST",
                headers: {
                    "Authorization": `Bearer ${token}`  // Add token in Authorization header as Bearer
                },
                data: JSON.stringify({question: userMessage}),
                contentType: "application/json",
                success: function (response) {
                    AIResponse = response;
                    for (let i = 0; i < response.length; i++) {
                        let chatbotResponse = response.substring(0, i + 1);
                        setTimeout(function () {
                            $("#answerBox").html(`<p>${chatbotResponse}</p>`);
                        }, 50 * i);
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        });
    });
});