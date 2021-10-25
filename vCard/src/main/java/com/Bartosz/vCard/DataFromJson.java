package com.Bartosz.vCard;

public class DataFromJson {
	String name;
	String image;
	String telephone;
	String email;
	String sameAs;
	String url;
	Address address;
	String addressAsString;

	public void setAddressAsString(String addressAsString) {
		this.addressAsString = addressAsString;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSameAs(String sameAs) {
		this.sameAs = sameAs;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAddressAsString() {
		if (addressAsString != null) {
			return addressAsString;
		}
		return "";
	}

	public String getName() {
		if (name != null) {
			return name;
		}
		return "";
	}

	public String getImage() {
		return image;
	}

	public String getTelephone() {
		if (telephone != null) {
			return telephone;
		}
		return "";
	}

	public String getEmail() {
		if (email != null) {
			return email;
		}
		return "";
	}

	public String getSameAs() {
		if (sameAs != null) {
			return sameAs;
		}
		return "";
	}

	public String getUrl() {
		if (url != null) {
			return url;
		}
		return "";
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		if (name != null) {
			stringBuilder.append(name).append(" ");
		}
		if (email != null) {
			stringBuilder.append(email).append(" ");
		}
		if (telephone != null) {
			stringBuilder.append(telephone).append(" ");
		}
		if (sameAs != null) {
			stringBuilder.append(sameAs).append(" ");
		}
		if (url != null) {
			stringBuilder.append(url).append(" ");
		}
		if (image != null) {
			stringBuilder.append(image).append(" ");
		}
		if (address != null) {
			stringBuilder.append(address.toString());
		}


		return stringBuilder.toString();
	}

}
