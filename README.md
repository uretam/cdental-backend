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

## 📋 Manual de Referencia de la API (Endpoints del Gateway)

> ⚠️ **Nota de enrutamiento:** Todas las peticiones deben enviarse exclusivamente al puerto del **API Gateway (9080)**. El encabezado `Content-Type: application/json` es obligatorio para métodos POST y PUT.

### 👥 1. Servicio de Pacientes

<table>
  <tr>
    <th align="left" width="150">Acción</th>
    <th align="left" width="100">Método</th>
    <th align="left">URL en Postman</th>
  </tr>
  <tr>
    <td><b>Crear Paciente</b></td>
    <td><img src="https://img.shields.io/badge/POST-green?style=flat-square" alt="POST"></td>
    <td><code>http://localhost:9080/api/pacientes</code></td>
  </tr>
  <tr>
    <td colspan="3">
      <b>Cuerpo de la Petición (JSON):</b>
<pre><code>{
  "nombres": "Juan Pablo",
  "apellidos": "Gómez Pérez",
  "cedula": "1725364728",
  "telefono": "+56987654321",
  "email": "juan.gomez@mail.com",
  "fechaNacimiento": "1990-05-15"
}</code></pre>
    </td>
  </tr>
  <tr>
    <td><b>Listar Pacientes</b></td>
    <td><img src="https://img.shields.io/badge/GET-blue?style=flat-square" alt="GET"></td>
    <td><code>http://localhost:9080/api/pacientes</code></td>
  </tr>
</table>

<br>

### 🦷 2. Servicio de Odontólogos

<table>
  <tr>
    <th align="left" width="150">Acción</th>
    <th align="left" width="100">Método</th>
    <th align="left">URL en Postman</th>
  </tr>
  <tr>
    <td><b>Crear Odontólogo</b></td>
    <td><img src="https://img.shields.io/badge/POST-green?style=flat-square" alt="POST"></td>
    <td><code>http://localhost:9080/api/odontologos</code></td>
  </tr>
  <tr>
    <td colspan="3">
      <b>Cuerpo de la Petición (JSON):</b>
<pre><code>{
  "nombres": "Ana María",
  "apellidos": "Silva Restrepo",
  "matricula": "ODONTO-2026-99A",
  "especialidad": "Endodoncia",
  "telefono": "+56911112222"
}</code></pre>
    </td>
  </tr>
  <tr>
    <td><b>Eliminar por ID</b></td>
    <td><img src="https://img.shields.io/badge/DELETE-red?style=flat-square" alt="DELETE"></td>
    <td><code>http://localhost:9080/api/odontologos/{id}</code></td>
  </tr>
</table>

<br>

### ⚡ 3. Servicio de Tratamientos

<table>
  <tr>
    <th align="left" width="150">Acción</th>
    <th align="left" width="100">Método</th>
    <th align="left">URL en Postman</th>
  </tr>
  <tr>
    <td><b>Crear Tratamiento</b></td>
    <td><img src="https://img.shields.io/badge/POST-green?style=flat-square" alt="POST"></td>
    <td><code>http://localhost:9080/api/tratamientos</code></td>
  </tr>
  <tr>
    <td colspan="3">
      <b>Cuerpo de la Petición (JSON):</b>
<pre><code>{
  "nombre": "Diseño de Sonrisa",
  "descripcion": "Procedimiento estético con carillas de porcelana",
  "costoBase": 1250.00,
  "duracionEstimadaMinutos": 120
}</code></pre>
    </td>
  </tr>
</table>

<br>

### 📅 4. Servicio de Citas Médicas

<table>
  <tr>
    <th align="left" width="150">Acción</th>
    <th align="left" width="100">Método</th>
    <th align="left">URL en Postman</th>
  </tr>
  <tr>
    <td><b>Agendar Cita</b></td>
    <td><img src="https://img.shields.io/badge/POST-green?style=flat-square" alt="POST"></td>
    <td><code>http://localhost:9080/api/citas</code></td>
  </tr>
  <tr>
    <td colspan="3">
      <b>Cuerpo de la Petición (JSON):</b>
<pre><code>{
  "pacienteId": 1,
  "odontologoId": 3,
  "tratamientoId": 2,
  "fechaHora": "2026-06-15T14:30:00",
  "motivoConsulta": "Control preventivo general",
  "estado": "PROGRAMADA"
}</code></pre>
    </td>
  </tr>
  <tr>
    <td><b>Cambiar Estado</b></td>
    <td><img src="https://img.shields.io/badge/PUT-orange?style=flat-square" alt="PUT"></td>
    <td><code>http://localhost:9080/api/citas/{id}/estado</code></td>
  </tr>
  <tr>
    <td colspan="3">
      <b>Cuerpo de la Petición (JSON):</b>
<pre><code>{
  "nuevoEstado": "COMPLETADA"
}</code></pre>
    </td>
  </tr>
</table>

<br>

### 💳 5. Servicio de Facturas

<table>
  <tr>
    <th align="left" width="150">Acción</th>
    <th align="left" width="100">Método</th>
    <th align="left">URL en Postman</th>
  </tr>
  <tr>
    <td><b>Emitir Factura</b></td>
    <td><img src="https://img.shields.io/badge/POST-green?style=flat-square" alt="POST"></td>
    <td><code>http://localhost:9080/api/facturas</code></td>
  </tr>
  <tr>
    <td colspan="3">
      <b>Cuerpo de la Petición (JSON):</b>
<pre><code>{
  "citaId": 1,
  "montoTotal": 450.00,
  "metodoPago": "TARJETA_CREDITO",
  "estado": "PAID"
}</code></pre>
    </td>
  </tr>
  <tr>
    <td><b>Reporte Ingresos</b></td>
    <td><img src="https://img.shields.io/badge/GET-blue?style=flat-square" alt="GET"></td>
    <td><code>http://localhost:9080/api/facturas/ingresos?inicio=2026-01-01&fin=2026-12-31</code></td>
  </tr>
</table>

---

## 🚀 Despliegue y Ejecución del Proyecto

### 1. Compilación Inicial (Raíz del Proyecto)
Abre una terminal en la carpeta principal del ecosistema y descarga todas las dependencias necesarias:
```bash
mvn clean install -DskipTests
