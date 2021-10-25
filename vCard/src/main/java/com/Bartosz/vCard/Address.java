package com.Bartosz.vCard;

public class Address {
	String streetAddress;
	String addressLocality;
	String addressCountry;

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setAddressLocality(String addressLocality) {
		this.addressLocality = addressLocality;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getAddressLocality() {
		return addressLocality;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	@Override
	public String toString() {
		return streetAddress + " " + addressLocality + " " + addressCountry;
	}
}
