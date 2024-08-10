# Usho

A Url Shortner without a hastle made with Spring which you can serve on your Tomcat server the way
you want. It is Restful and you can elevate it to set up your own Url Shortner with any frontend 
you'd like

## How does it work?

Basically it's **Feemium** based and there are two modes to use this web-based program:

+ Free Mode: There are **5 Common Namespaces** Each surfer on the web can access without a hastle.
They can make whatever source they want unless already taken before. links generated for public 
usage will be destroyed in **24 hours** 

+ Premium Mode: there are people (I guess mostly corporate or business folks) who want to use this 
program to have a better looking urls, and should be able to manage their urls and **See Insights**.
their links will **Never Expire**

## Techinal

I use Jdbc, bc first off I hate __JPA__ and any other ORM ftm. and second, I want more control over 
everything in my application. most of the time I use __JPA__ I just end up writing the same amount
of sql but with too much effort to convert them into JPA specific code

Please note that Spring Jdbc is being used **NOT** Spring __Data__ Jdbc! so you're gonna have to
write all the juicy boilerplate yourself. You can replace it in the repositories I provided as 
interfaces if you don't like Jdbc

## TODO
- [ ] __Jdbc Security__ and User Management for better managing namespaces
- [ ] __url_access__ table for insights and it's counter part service and db repository
