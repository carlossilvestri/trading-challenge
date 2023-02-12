// Create a connection to http://localhost:9999/echo
let sock = new SockJS('http://localhost:9090/ws');

let stompClient = Stomp.over(sock);
stompClient.connect({}, function(frame) {
    stompClient.debug("connected to Stomp");
    stompClient.subscribe("/topic/messages", function(message) {
        console.log(message);
    });
});

// Open the connection
sock.onopen = function() {
  console.log('open');
    // stompClient.send("/app/btcusdt@aggTrade", {}, "HOLAAAA");
};

// On connection close
sock.onclose = function() {
  console.log('close');
};



  // Function for sending the message to server
function sendMessage(){
    // Get the content from the textbox
    var message = $('#message').val();
    var username = $('#username').val();
  
    // The object to send
    var send = {
      message: message,
      username: username
    };
  
    // Send it now
    sock.send(JSON.stringify(send));
  }