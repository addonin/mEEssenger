$(document).ready(function() {
    
    $(".friend").on('click', function() {
        $('#toId').val($(this).find('span').attr('id').substring(1));        
        $('#chatWindow').css("visibility", "visible");
    });
    
    $(document).mouseup(function (e) {
        var button = $(".friend");
        var chatWindow = $("#chatWindow");

        if (!button.is(e.target) && !chatWindow.is(e.target)
            && button.has(e.target).length === 0 && chatWindow.has(e.target).length === 0)
        {
            $("#chatWindow").css("visibility", "hidden");
        }
    });
    
    $('#messageInput').keydown(function (e) {
        if ((e.keyCode === 10 || e.keyCode === 13) && e.ctrlKey) {
            $("#sendMessageForm\\:sendButton").click();
        }
    });

});

function sendMessage(data) {
    var status = data.status;
    switch(status) {
        case "begin":
            // This is invoked right before ajax request is sent.
            $('#timestamp').val(new Date().getTime());
            break;

        case "complete":
            // This is invoked right after ajax response is returned.
            break;

        case "success":
            // This is invoked right after successful processing of ajax response and update of HTML DOM.
            $("#messageOutput").append("<span style='float: left'><span style='color: red'>(" + new Date(parseInt($('#timestamp').val())).toISOString().replace('T', ' ').slice(0, 19) + 
                    ")</span> " + "<span style='font-weight: bold; display:block'>" + $('#user').val() + "</span><br><span style='display: block'>" + $("#messageInput").val() + "</span><br><br></span>");
            $('#messageInput').val('');
            break;
    }
}    



