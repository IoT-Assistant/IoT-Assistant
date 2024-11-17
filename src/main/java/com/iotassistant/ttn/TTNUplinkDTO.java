package com.iotassistant.ttn;

class TTNUplinkDTO {
	
	String received_at;
	
	TTNUplinkMessageDTO uplink_message;

	public TTNUplinkDTO() {
		super();
	}

	public String getReceived_at() {
		return received_at;
	}

	public void setReceived_at(String received_at) {
		this.received_at = received_at;
	}

	public TTNUplinkMessageDTO getUplink_message() {
		return uplink_message;
	}

	public void setUplink_message(TTNUplinkMessageDTO uplink_message) {
		this.uplink_message = uplink_message;
	}
	
	public String getPayload() {
		return this.uplink_message.getFrm_payload();
	}
}
