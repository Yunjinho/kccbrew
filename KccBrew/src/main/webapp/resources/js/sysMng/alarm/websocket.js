function sendUserId() {
	if (typeof sockjs !== 'undefined') {
		var userData = {
				userId: userId 
		};
		var jsonData = JSON.stringify(userData);
		sockjs.send(jsonData);
		console.log("sendUserId함수실행!");
	} else {
		
	}
}
