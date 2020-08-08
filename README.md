# citiesConnected

This is just a random program for me to better understand Graph architecture and custom classes.

A program that uses Spring Boot and Java to determine if two cities are connected.
Two cities are considered connected if there's a series of roads that can be traveled from one city to another.

Input: A list of roads is available in a file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a
road between those cities.
Output: Returns 'yes' if city1 is connected to city2, 'no' if city1 is not connected to city2. Any unexpected input should result in a 'no' response.

It will be deployed as a Spring Boot App and expose one endpoint: http://localhost:8080/connected?origin=city1&destination=city2

For Example:

city.txt content is:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany

http://localhost:8080/connected?origin=Boston&destination=Newark
Should return yes
http://localhost:8080/connected?origin=Boston&destination=Philadelphia
Should return yes
http://localhost:8080/connected?origin=Philadelphia&destination=Albany
Should return no
