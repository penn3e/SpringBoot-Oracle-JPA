# RestAPI Springboot Oracle

## Lancer Oracle Database 21c Express avec Docker
```sh
# Lancer Oracle Database 21c Express avec Docker
docker container create -it --name oracle-test -p 1521:1521 -e ORACLE_PWD=welcome123 container-registry.oracle.com/database/express:latest
```

## Méthodes et Points de Terminaison

| Méthode HTTP | URL | Description |
|-------------|----------------------------|----------------------------------------------|
| GET | `/employees` | Obtenir tous les employés (pagination) |
| GET | `/employees/{id}` | Obtenir un employé par son ID |
| POST | `/employees` | Créer un nouvel employé |
| PUT | `/employees/{id}` | Mettre à jour un employé existant |
| DELETE | `/employees/{id}` | Supprimer un employé |
| GET | `/employees/by-name?name={name}` | Rechercher des employés par nom |
| GET | `/employees/by-job-title?jobTitle={jobTitle}` | Rechercher des employés par poste |
| GET | `/employees/high-salary?threshold={threshold}` | Rechercher des employés avec un salaire supérieur à un seuil |

## Exemples d'Utilisation

### 1. Créer un employé
```http
POST /employees
Content-Type: application/json

{
  "name": "Alice Johnson",
  "jobTitle": "Data Scientist",
  "salary": 6000.00
}
```

### 2. Obtenir tous les employés (pagination)
```http
GET /employees?page=0&size=10
```

### 3. Obtenir un employé par ID
```http
GET /employees/1
```

### 4. Mettre à jour un employé
```http
PUT /employees/1
Content-Type: application/json

{
  "name": "John Doe Updated",
  "jobTitle": "Senior Software Engineer",
  "salary": 5500.00
}
```

### 5. Supprimer un employé
```http
DELETE /employees/1
```

### 6. Rechercher des employés par nom
```http
GET /employees/by-name?name=John
```

### 7. Rechercher des employés par poste
```http
GET /employees/by-job-title?jobTitle=Software Engineer
```

### 8. Rechercher des employés avec un salaire supérieur à un seuil
```http
GET /employees/high-salary?threshold=6000
```

## Notes
- L'API supporte la pagination avec les paramètres `page` et `size`.
- Toutes les requêtes nécessitant un corps doivent être au format JSON.

