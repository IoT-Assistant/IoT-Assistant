class TelegramSettings {

	constructor(token, chatId, username) {
		this.token = token;
		this.chatId = chatId;
		this.username = username;
    }
    
    getToken() {
		return this.token;
	}
	
	getChatId() {
		return this.chatId;
	}
	
	getUsername() {
		return this.username;
	}
	
	
}