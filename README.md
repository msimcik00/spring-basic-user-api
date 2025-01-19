# USER API
## Požadavky
- **Java 21 nebo vyšší**
- **Maven**

## Spuštění aplikace
1. Naklonujte repozitář
2. Přejděte do kořenového adresáře projektu
3. Spusťte aplikaci pomocí Mavenu: 
```bash
mvn spring-boot:run
```
4. Aplikace se poté spustí na adrese `http://localhost:8080`.

## Dostupné endpointy
Všechny endpointy pod `/api/users` vyžadují Basic Authentication.

### Výchozí přihlašovací údaje:
- **Uživatelské jméno:** admin
- **Heslo:** admin123

### Seznam endpointů
| Endpoint | Metoda | Popis | Autentizace |
|----------|--------|-------|-------------|
| `/api/users` | GET | Vrátí seznam všech uživatelů | Vyžadována |
| `/api/users/{userId}` | GET | Vrátí detaily konkrétního uživatele | Vyžadována |




