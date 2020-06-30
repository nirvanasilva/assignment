
# Assignment Scalable Web

## Description

Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
* **\<host\>**/v1/diff/**\<ID\>**/left and **\<host\>**/v1/diff/**\<ID\>**/right

The provided data needs to be diff-ed and the results shall be available on a third end point

* **\<host\>**/v1/diff/**\<ID\>**

The results shall provide the following info in JSON format

 * If equal return that
 * If not of equal size just return that
 * If of same size provide insight in where the diffs are, actual diffs are not needed.
	 * So mainly offsets + length in the data

## Solution
TBD

## Requirements
* Java 8

## How to use it
### Run
In the terminal, use the following command:

    mvn spring-boot:run

Application will be available at: `http://localhost:8080/`

### Use
1. Add left and right data to be diff-ed
* Endpoints:  
	* POST `http://localhost:8080/v1/diff/<ID>/left` or `http://localhost:8080/v1/diff/<ID>/right` 
* Header:
	* Content-Type: application/json
* Parameter
	* **\<ID\>** is a number identifying the data to be diff-ed. 
* Payload:
	* JSON containing Base64 encoded binary data following the format below:

Sample payload:

    {
	    "data": "samplQ=="
    }

2. Get diff between left and right data by making a GET request to `http://localhost:8080/v1/diff/<ID>`. **\<ID\>** is a number identifying the data to be diff-ed.

## Tests
To run the tests:

    mvn clean test

