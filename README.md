- Exercise: Construir una aplicación/servicio en SpringBoot que provea una end
point rest de consulta tal que:
Acepte como parámetros de entrada: fecha de aplicación, identificador
de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto,
identificador de cadena, tarifa a aplicar, fechas de aplicación y
precio final a aplicar.
Se debe utilizar una base de datos en memoria (tipo h2) e inicializar
con los datos del ejemplo, (se pueden cambiar el nombre de los campos
y añadir otros nuevos si se quiere, elegir el tipo de dato que se
considere adecuado para los mismos).


- DER
![InnoIt-prices-DER.png](db-files%2FInnoIt-prices-DER.png)

 
- From the maven command line profile local
  - mvnw spring-boot:run -Dspring-boot.run.profiles=h2
  - Endpoint
    - http://localhost:8080/innoIt-test/api/calculate/prices
    - request body example:
      ````
      {
      "applicationDate": "2020-06-14T16:45:07Z",
      "brandId": "1",
      "productId": "35455"
      }
      ````


[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=renanzazula_innoIt-test-price-calculation)](https://sonarcloud.io/summary/new_code?id=renanzazula_innoIt-test-price-calculation)
