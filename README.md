DentalCloud - Sistema Integral de Gestión Clínica (Microservicios)
Plataforma backend empresarial diseñada bajo una arquitectura de Microservicios Decoplados para la administración de clínicas odontológicas. Permite gestionar de manera escalable el registro de pacientes, agendas de citas, catálogo de tratamientos médicos, personal de odontólogos y procesos de facturación/auditoría financiera.

Arquitectura del Sistema y Mapeo de Puertos
El sistema implementa el patrón API Gateway como punto único de entrada perimetral. Todas las peticiones de los clientes se centralizan en el puerto público 9080, el cual rutea el tráfico hacia los servicios correspondientes en la red privada local:

API GATEWAY -> Puerto: 9080

Microservicio Pacientes -> Puerto: 9081

Microservicio Citas -> Puerto: 9082

Microservicio Odontólogos -> Puerto: 9083

Microservicio Tratamientos -> Puerto: 9084

Microservicio Facturas -> Puerto: 9085

Stack Tecnológico
Core Runtime: Java 21 (LTS) & Spring Boot 3.5.14

Ecosistema Cloud: Spring Cloud Gateway 2025.0.2 (Servidor Reactivo Netty)

Persistencia: Spring Data JPA, Hibernate 6.6, HikariCP

Base de Datos: Instancias independientes de MySQL 8.0 por microservicio

Evolución del Esquema: Liquibase 4.31 (Migración y carga de datos semilla automática)

Configuración de Base de Datos (MySQL)
Antes del primer arranque, crea los siguientes esquemas en tu servidor local. Liquibase se encargará de crear las tablas e insertar los datos semilla automáticamente:

CREATE DATABASE IF NOT EXISTS cdent_pacientes;
CREATE DATABASE IF NOT EXISTS cdent_odontologos;
CREATE DATABASE IF NOT EXISTS cdent_citas;
CREATE DATABASE IF NOT EXISTS cdent_tratamientos;
CREATE DATABASE IF NOT EXISTS cdent_facturas;

Manual de Referencia de la API (Endpoints por Gateway)
Importante: Todas las peticiones deben enviarse al puerto del API Gateway (9080) añadiendo el encabezado "Content-Type: application/json".

1. Microservicio de Pacientes (/api/pacientes)
CREAR PACIENTE

Método: POST

URL: http://localhost:9080/api/pacientes

Estructura Body JSON:
{
"nombres": "Juan Pablo",
"apellidos": "Gómez Pérez",
"cedula": "1725364728",
"telefono": "+56987654321",
"email": "juan.gomez@mail.com",
"fechaNacimiento": "1990-05-15"
}

LISTAR PACIENTES

Método: GET

URL: http://localhost:9080/api/pacientes

2. Microservicio de Odontólogos (/api/odontologos)
CREAR ODONTÓLOGO

Método: POST

URL: http://localhost:9080/api/odontologos

Estructura Body JSON:
{
"nombres": "Ana María",
"apellidos": "Silva Restrepo",
"matricula": "ODONTO-2026-99A",
"especialidad": "Endodoncia",
"telefono": "+56911112222"
}

ELIMINAR ODONTÓLOGO

Método: DELETE

URL: http://localhost:9080/api/odontologos/1

3. Microservicio de Tratamientos (/api/tratamientos)
CREAR TRATAMIENTO

Método: POST

URL: http://localhost:9080/api/tratamientos

Estructura Body JSON:
{
"nombre": "Diseño de Sonrisa",
"descripcion": "Procedimiento estético con carillas de porcelana",
"costoBase": 1250.00,
"duracionEstimadaMinutos": 120
}

DETALLE DE TRATAMIENTO

Método: GET

URL: http://localhost:9080/api/tratamientos/1

4. Microservicio de Citas Médicas (/api/citas)
AGENDAR CITA

Método: POST

URL: http://localhost:9080/api/citas

Estructura Body JSON:
{
"pacienteId": 1,
"odontologoId": 3,
"tratamientoId": 2,
"fechaHora": "2026-06-15T14:30:00",
"motivoConsulta": "Control preventivo general",
"estado": "PROGRAMADA"
}

ACTUALIZAR ESTADO

Método: PUT

URL: http://localhost:9080/api/citas/1/estado

Estructura Body JSON:
{
"nuevoEstado": "COMPLETADA"
}

5. Microservicio de Facturas (/api/facturas)
EMITIR FACTURA

Método: POST

URL: http://localhost:9080/api/facturas

Estructura Body JSON:
{
"citaId": 1,
"montoTotal": 450.00,
"metodoPago": "TARJETA_CREDITO",
"estado": "PAID"
}

REPORTE DE INGRESOS POR RANGO (JPQL Optimizado)

Método: GET

URL: http://localhost:9080/api/facturas/ingresos?inicio=2026-01-01&fin=2026-12-31

Despliegue y Ejecución del Proyecto
Compilación Inicial (Raíz):
Ejecuta en la carpeta raíz del proyecto para descargar dependencias:
mvn clean install -DskipTests

Orden de Inicialización:
Levanta los microservicios core simultáneamente (pacientes, odontologos, tratamientos, citas, facturas) ejecutando el siguiente comando en sus carpetas respectivas:
mvn spring-boot:run

Levanta el API Gateway al final ejecutando en su carpeta correspondiente:
mvn spring-boot:run

Buenas Prácticas Aplicadas
Validación en Tiempo de Compilación: Consultas JPQL personalizadas mapeadas estrictamente a las Clases Entidad de Java (ej: Factura) respetando mayúsculas exigidas por Hibernate 6.

Enrutamiento Seguro: Implementación de un Gateway reactivo perimetral evitando la duplicación del contexto de la URL (/api/).

Control de Entorno: Archivo .gitignore configurado para prevenir la subida de carpetas de compilación (/target) o configuraciones de IDE locales.
