# Test Project

technologies demonstrated 
* Spring Boot 3
* Liquibase
* Hibernate
* OpenAPI (http://localhost:8080/swagger-ui/index.html)

```shell
docker compose up
```

```shell
➜  nbd0524 curl -X 'POST' \
  'http://localhost:8080/checkout' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "tool_code": "JAKR",
  "rental_day_count": 4,
  "discount_percentage": 50,
  "checkout_date": "07/02/20"
}' | jq .




{
  "tool_code": "JAKR",
  "rental_day_count": 4,
  "discount_percentage": 50,
  "checkout_date": "07/02/20",
  "tool_type": "Jackhammer",
  "tool_brand": "Ridgid",
  "due_date": "07/06/20",
  "daily_rental_charge": 2.99,
  "charge_days": 2,
  "pre-discount_charge": 5.98,
  "discount_amount": 2.99,
  "final_charge": 2.99
}

```