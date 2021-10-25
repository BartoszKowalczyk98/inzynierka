# Read me first
Spring project to get data from user to look for company offers based on input phrase or localization, and then
 process retrieved data. Then present that data and allow user to generate vCard of that company.

```
http://localhost:8080/
```
Will lead user basic input site with 2 input boxes, and after clicking button named "szukaj" it will forward a
 request to /search endpoint with two form fields:
 * k - field of work where user searches a specialist
 * l - localization where one should be found

Program will then search all possiblities form 1st page of https://panoramafirm.pl/ and retrieve data of all
 companies and present user with scrollable list. Every position in that list has a button that allows generating a
  .vcf file and download it to local machine.
  Lastly endpoint /generate-vcard takes following form parameters:  
 * name - name of company
 * telephone - phone number of company
 * email - email address of chosen company
 * url - link to site if company has one
 * addressAsString - whole local address of company headquarters
 
 
# Example .vcf file content
```
BEGIN:VCARD
VERSION:4.0
ORG:Bartosz Kowalczyk
TEL:123456789
ADR: Kowalska 1/2 Boat PL
EMAIL:biuro@test.com.pl
URL:http://test.com.pl
END:VCARD
```
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Main Gson github page](https://github.com/google/gson)
* [Json documentation used during development](https://jsoup.org/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


