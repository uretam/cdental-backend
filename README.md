# 🦷 DentalCloud - Sistema Integral de Gestión Clínica

<div align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring_Boot-3.5.14-brightgreen?style=for-the-badge&logo=springboot" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Spring_Cloud-2025.0.2-blue?style=for-the-badge&logo=spring" alt="Spring Cloud">
  <img src="https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql" alt="MySQL">
  <img src="https://img.shields.io/badge/Liquibase-4.31-red?style=for-the-badge&logo=liquibase" alt="Liquibase">
</div>

<br>

> 🚀 **DentalCloud** es una plataforma backend empresarial diseñada bajo una arquitectura de **Microservicios Decoplados** para la administración de clínicas odontológicas. Permite gestionar de manera escalable el registro de pacientes, agendas de citas, catálogo de tratamientos médicos, personal de odontólogos y procesos de facturación.

---

## 🏗️ Arquitectura del Sistema y Puertos

<table border="1">
  <tr>
    <td><strong>Componente</strong></td>
    <td><strong>Puerto</strong></td>
    <td><strong>Ruta Base (Gateway)</strong></td>
  </tr>
  <tr>
    <td>🛡️ API Gateway (Netty)</td>
    <td><code>9080</code></td>
    <td><code>/</code></td>
  </tr>
  <tr>
    <td>👥 Servicio Pacientes</td>
    <td><code>9081</code></td>
    <td><code>/api/pacientes</code></td>
  </tr>
  <tr>
    <td>📅 Servicio Citas</td>
    <td><code>9082</code></td>
    <td><code>/api/citas</code></td>
  </tr>
  <tr>
    <td>🦷 Servicio Odontólogos</td>
    <td><code>9083</code></td>
    <td><code>/api/odontologos</code></td>
  </tr>
  <tr>
    <td>⚡ Servicio Tratamientos</td>
    <td><code>9084</code></td>
    <td><code>/api/tratamientos</code></td>
  </tr>
  <tr>
    <td>💳 Servicio Facturas</td>
    <td><code>9085</code></td>
    <td><code>/api/facturas</code></td>
  </tr>
</table>

---

## ⚙️ Configuración de Base de Datos (MySQL)

Cada microservicio gestiona su base de datos de forma aislada. Crea los siguientes esquemas en tu MySQL local antes de arrancar. **Liquibase creará las tablas y datos semilla automáticamente**:

```sql
CREATE DATABASE IF NOT EXISTS cdent_pacientes;
CREATE DATABASE IF NOT EXISTS cdent_odontologos;
CREATE DATABASE IF NOT EXISTS cdent_citas;
CREATE DATABASE IF NOT EXISTS cdent_tratamientos;
CREATE DATABASE IF NOT EXISTS cdent_facturas;
📋 Manual de Referencia de la API (Endpoints del Gateway)
⚠️ Nota: Todas las peticiones deben enviarse al puerto del API Gateway (9080) incluyendo el encabezado Content-Type: application/json.

👥 1. Servicio de Pacientes
URL: http://localhost:9080/api/pacientes

Cuerpo de la Petición (JSON):

JSON
{
  "nombres": "Juan Pablo",
  "apellidos": "Gómez Pérez",
  "cedula": "1725364728",
  "telefono": "+56987654321",
  "email": "juan.gomez@mail.com",
  "fechaNacimiento": "1990-05-15"
}
URL: http://localhost:9080/api/pacientes

🦷 2. Servicio de Odontólogos
URL: http://localhost:9080/api/odontologos

Cuerpo de la Petición (JSON):

JSON
{
  "nombres": "Ana María",
  "apellidos": "Silva Restrepo",
  "matricula": "ODONTO-2026-99A",
  "especialidad": "Endodoncia",
  "telefono": "+56911112222"
}
URL: http://localhost:9080/api/odontologos/1

⚡ 3. Servicio de Tratamientos
URL: http://localhost:9080/api/tratamientos

Cuerpo de la Petición (JSON):

JSON
{
  "nombre": "Diseño de Sonrisa",
  "descripcion": "Procedimiento estético con carillas de porcelana",
  "costoBase": 1250.00,
  "duracionEstimadaMinutos": 120
}
📅 4. Servicio de Citas Médicas
URL: http://localhost:9080/api/citas

Cuerpo de la Petición (JSON):

JSON
{
  "pacienteId": 1,
  "odontologoId": 3,
  "tratamientoId": 2,
  "fechaHora": "2026-06-15T14:30:00",
  "motivoConsulta": "Control preventivo general",
  "estado": "PROGRAMADA"
}
URL: http://localhost:9080/api/citas/1/estado

Cuerpo de la Petición (JSON):

JSON
{
  "nuevoEstado": "COMPLETADA"
}
💳 5. Servicio de Facturas
URL: http://localhost:9080/api/facturas

Cuerpo de la Petición (JSON):

JSON
{
  "citaId": 1,
  "montoTotal": 450.00,
  "metodoPago": "TARJETA_CREDITO",
  "estado": "PAID"
}
URL: http://localhost:9080/api/facturas/ingresos?inicio=2026-01-01&fin=2026-12-31

🚀 Despliegue y Ejecución del Proyecto
1. Compilación Inicial (Raíz)
Bash
mvn clean install -DskipTests
2. Orden de Inicialización
Inicia los microservicios core simultáneamente ejecutando el siguiente comando en sus respectivas carpetas:

Bash
mvn spring-boot:run
Al final, levanta el componente api-gateway usando el mismo comando.

🛠️ Buenas Prácticas Aplicadas
Validación en Tiempo de Compilación: Consultas JPQL personalizadas mapeadas estrictamente a las Clases Entidad de Java (ej: Factura) respetando las mayúsculas exigidas por Hibernate 6.

Enrutamiento Seguro: Implementación de un Gateway reactivo perimetral que evita la duplicación del contexto de la URL (/api/).

Control de Entorno: Archivo .gitignore configurado rigurosamente a nivel raíz para prevenir la subida de datos temporales (/target) o configuraciones locales del IDE.
