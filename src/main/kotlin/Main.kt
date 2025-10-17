package org.example

import org.http4k.core.*
import org.http4k.core.Method.*
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.format.Jackson.auto

// Data classes para las peticiones
data class ProyectoRequest(val nombre: String, val descripcion: String)

fun main() {
    // Lenses para extraer/enviar JSON
    val proyectoRequestLens = Body.auto<ProyectoRequest>().toLens()
    val proyectosLens = Body.auto<List<Proyecto>>().toLens()

    val gestorDeTareas = GestorDeTareas()

    val app = routes(
        "/" bind GET to { request: Request ->
            val html = """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Gestor de Tareas - Sistema Completo</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }

                        :root {
                            --primary: #667eea;
                            --primary-dark: #5568d3;
                            --secondary: #764ba2;
                            --success: #10b981;
                            --warning: #f59e0b;
                            --danger: #ef4444;
                            --dark: #1f2937;
                            --light: #f9fafb;
                            --border: #e5e7eb;
                        }

                        body {
                            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            min-height: 100vh;
                            padding: 20px;
                            color: var(--dark);
                        }

                        .main-container {
                            max-width: 1400px;
                            margin: 0 auto;
                        }

                        .header {
                            background: white;
                            border-radius: 20px;
                            padding: 30px;
                            margin-bottom: 30px;
                            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            flex-wrap: wrap;
                            gap: 20px;
                        }

                        .header-title {
                            display: flex;
                            align-items: center;
                            gap: 15px;
                        }

                        .logo {
                            width: 60px;
                            height: 60px;
                            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
                            border-radius: 15px;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-size: 30px;
                            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.3);
                        }

                        h1 {
                            font-size: 2em;
                            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
                            -webkit-background-clip: text;
                            -webkit-text-fill-color: transparent;
                            background-clip: text;
                        }

                        .stats {
                            display: flex;
                            gap: 20px;
                        }

                        .stat-item {
                            text-align: center;
                            padding: 10px 20px;
                            background: var(--light);
                            border-radius: 12px;
                        }

                        .stat-number {
                            font-size: 24px;
                            font-weight: bold;
                            color: var(--primary);
                        }

                        .stat-label {
                            font-size: 12px;
                            color: #6b7280;
                            text-transform: uppercase;
                            margin-top: 5px;
                        }

                        .content-grid {
                            display: grid;
                            grid-template-columns: 1fr 2fr;
                            gap: 30px;
                        }

                        .sidebar {
                            background: white;
                            border-radius: 20px;
                            padding: 30px;
                            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
                            height: fit-content;
                            position: sticky;
                            top: 20px;
                        }

                        .main-content {
                            background: white;
                            border-radius: 20px;
                            padding: 30px;
                            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
                        }

                        .nav-menu {
                            list-style: none;
                        }

                        .nav-item {
                            padding: 15px 20px;
                            margin-bottom: 8px;
                            border-radius: 12px;
                            cursor: pointer;
                            transition: all 0.3s;
                            display: flex;
                            align-items: center;
                            gap: 12px;
                            font-weight: 500;
                            color: #6b7280;
                        }

                        .nav-item:hover {
                            background: var(--light);
                            color: var(--primary);
                            transform: translateX(5px);
                        }

                        .nav-item.active {
                            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
                            color: white;
                            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.3);
                        }

                        .nav-icon {
                            font-size: 20px;
                        }

                        .section {
                            display: none;
                            animation: fadeIn 0.5s;
                        }

                        .section.active {
                            display: block;
                        }

                        @keyframes fadeIn {
                            from { opacity: 0; transform: translateY(20px); }
                            to { opacity: 1; transform: translateY(0); }
                        }

                        .section-header {
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            margin-bottom: 30px;
                            padding-bottom: 20px;
                            border-bottom: 2px solid var(--border);
                        }

                        .section-title {
                            font-size: 1.8em;
                            color: var(--dark);
                        }

                        .form-grid {
                            display: grid;
                            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                            gap: 20px;
                            margin-bottom: 25px;
                        }

                        .form-group {
                            display: flex;
                            flex-direction: column;
                        }

                        .form-group label {
                            font-weight: 600;
                            margin-bottom: 8px;
                            color: var(--dark);
                            font-size: 14px;
                        }

                        .form-group input,
                        .form-group textarea,
                        .form-group select {
                            padding: 12px 16px;
                            border: 2px solid var(--border);
                            border-radius: 10px;
                            font-size: 14px;
                            transition: all 0.3s;
                            font-family: inherit;
                        }

                        .form-group input:focus,
                        .form-group textarea:focus,
                        .form-group select:focus {
                            outline: none;
                            border-color: var(--primary);
                            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
                        }

                        .form-group textarea {
                            resize: vertical;
                            min-height: 100px;
                        }

                        .btn {
                            padding: 12px 30px;
                            border: none;
                            border-radius: 10px;
                            font-size: 15px;
                            font-weight: 600;
                            cursor: pointer;
                            transition: all 0.3s;
                            display: inline-flex;
                            align-items: center;
                            gap: 8px;
                            text-decoration: none;
                        }

                        .btn-primary {
                            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
                            color: white;
                            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.3);
                        }

                        .btn-primary:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 8px 25px rgba(102, 126, 234, 0.4);
                        }

                        .btn-success {
                            background: var(--success);
                            color: white;
                        }

                        .btn-danger {
                            background: var(--danger);
                            color: white;
                        }

                        .btn-small {
                            padding: 6px 15px;
                            font-size: 13px;
                        }

                        .cards-container {
                            display: grid;
                            grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
                            gap: 20px;
                            margin-top: 20px;
                        }

                        .card {
                            background: linear-gradient(135deg, #f9fafb 0%, #ffffff 100%);
                            border-radius: 15px;
                            padding: 25px;
                            border: 2px solid var(--border);
                            transition: all 0.3s;
                            position: relative;
                            overflow: hidden;
                        }

                        .card::before {
                            content: '';
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 4px;
                            height: 100%;
                            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
                        }

                        .card:hover {
                            transform: translateY(-5px);
                            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.1);
                            border-color: var(--primary);
                        }

                        .card-header {
                            display: flex;
                            justify-content: space-between;
                            align-items: start;
                            margin-bottom: 15px;
                        }

                        .card-title {
                            font-size: 1.3em;
                            font-weight: 700;
                            color: var(--dark);
                            line-height: 1.3;
                        }

                        .card-id {
                            background: var(--primary);
                            color: white;
                            padding: 4px 12px;
                            border-radius: 20px;
                            font-size: 12px;
                            font-weight: 600;
                        }

                        .card-description {
                            color: #6b7280;
                            line-height: 1.6;
                            margin-bottom: 15px;
                        }

                        .empty-state {
                            text-align: center;
                            padding: 60px 20px;
                            color: #9ca3af;
                        }

                        .empty-state-icon {
                            font-size: 80px;
                            margin-bottom: 20px;
                            opacity: 0.3;
                        }

                        .empty-state-text {
                            font-size: 18px;
                            font-weight: 600;
                        }

                        .alert {
                            padding: 15px 20px;
                            border-radius: 12px;
                            margin-bottom: 20px;
                            display: flex;
                            align-items: center;
                            gap: 12px;
                            animation: slideIn 0.3s;
                        }

                        @keyframes slideIn {
                            from { transform: translateY(-20px); opacity: 0; }
                            to { transform: translateY(0); opacity: 1; }
                        }

                        .alert-success {
                            background: #d1fae5;
                            color: #065f46;
                            border: 2px solid #6ee7b7;
                        }

                        .alert-error {
                            background: #fee2e2;
                            color: #991b1b;
                            border: 2px solid #fca5a5;
                        }

                        .alert-icon {
                            font-size: 24px;
                        }

                        @media (max-width: 1024px) {
                            .content-grid {
                                grid-template-columns: 1fr;
                            }

                            .sidebar {
                                position: static;
                            }

                            .nav-menu {
                                display: grid;
                                grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
                                gap: 10px;
                            }
                        }

                        @media (max-width: 768px) {
                            .header {
                                text-align: center;
                            }

                            .stats {
                                flex-wrap: wrap;
                                justify-content: center;
                            }

                            .cards-container {
                                grid-template-columns: 1fr;
                            }

                            .form-grid {
                                grid-template-columns: 1fr;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="main-container">
                        <div class="header">
                            <div class="header-title">
                                <div class="logo">📋</div>
                                <div>
                                    <h1>Gestor de Tareas</h1>
                                    <p style="color: #6b7280; margin-top: 5px;">Sistema completo de gestión de proyectos</p>
                                </div>
                            </div>
                            <div class="stats">
                                <div class="stat-item">
                                    <div class="stat-number" id="stat-proyectos">0</div>
                                    <div class="stat-label">Proyectos</div>
                                </div>
                                <div class="stat-item">
                                    <div class="stat-number" id="stat-tareas">0</div>
                                    <div class="stat-label">Tareas</div>
                                </div>
                                <div class="stat-item">
                                    <div class="stat-number" id="stat-usuarios">0</div>
                                    <div class="stat-label">Usuarios</div>
                                </div>
                            </div>
                        </div>

                        <div class="content-grid">
                            <aside class="sidebar">
                                <h3 style="margin-bottom: 20px; color: var(--dark);">Navegación</h3>
                                <ul class="nav-menu">
                                    <li class="nav-item active" onclick="showSection('proyectos')">
                                        <span class="nav-icon">📁</span>
                                        <span>Proyectos</span>
                                    </li>
                                    <li class="nav-item" onclick="showSection('tareas')">
                                        <span class="nav-icon">✅</span>
                                        <span>Tareas</span>
                                    </li>
                                    <li class="nav-item" onclick="showSection('usuarios')">
                                        <span class="nav-icon">👥</span>
                                        <span>Usuarios</span>
                                    </li>
                                </ul>
                            </aside>

                            <main class="main-content">
                                <div id="alert-container"></div>

                                <!-- SECCIÓN PROYECTOS -->
                                <div id="proyectos" class="section active">
                                    <div class="section-header">
                                        <h2 class="section-title">📁 Gestión de Proyectos</h2>
                                    </div>

                                    <div class="form-grid">
                                        <div class="form-group">
                                            <label>Nombre del Proyecto</label>
                                            <input type="text" id="proyecto-nombre" placeholder="Ej: Aplicación Móvil">
                                        </div>
                                        <div class="form-group" style="grid-column: span 1;">
                                            <label>Descripción</label>
                                            <textarea id="proyecto-descripcion" placeholder="Describe el proyecto..." style="min-height: 60px;"></textarea>
                                        </div>
                                    </div>

                                    <button class="btn btn-primary" onclick="crearProyecto()">
                                        <span>➕</span>
                                        Crear Proyecto
                                    </button>

                                    <h3 style="margin-top: 40px; margin-bottom: 20px; color: var(--dark);">Lista de Proyectos</h3>
                                    <div id="lista-proyectos" class="cards-container"></div>
                                </div>

                                <!-- SECCIÓN TAREAS -->
                                <div id="tareas" class="section">
                                    <div class="section-header">
                                        <h2 class="section-title">✅ Gestión de Tareas</h2>
                                    </div>
                                    <p>Sección de tareas en desarrollo...</p>
                                </div>

                                <!-- SECCIÓN USUARIOS -->
                                <div id="usuarios" class="section">
                                    <div class="section-header">
                                        <h2 class="section-title">👥 Gestión de Usuarios</h2>
                                    </div>
                                    <p>Sección de usuarios en desarrollo...</p>
                                </div>
                            </main>
                        </div>
                    </div>

                    <script>
                        const API_BASE = 'http://localhost:8080';

                        function showSection(sectionId) {
                            document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
                            document.querySelectorAll('.nav-item').forEach(t => t.classList.remove('active'));
                            document.getElementById(sectionId).classList.add('active');
                            event.target.closest('.nav-item').classList.add('active');
                            
                            if (sectionId === 'proyectos') {
                                cargarProyectos();
                            }
                        }

                        function showAlert(message, type = 'success') {
                            const alertContainer = document.getElementById('alert-container');
                            const alert = document.createElement('div');
                            alert.className = `alert alert-${'$'}{type}`;
                            alert.innerHTML = `
                                <span class="alert-icon">${'$'}{type === 'success' ? '✓' : '⚠'}</span>
                                <span>${'$'}{message}</span>
                            `;
                            alertContainer.appendChild(alert);
                            setTimeout(() => alert.remove(), 3000);
                        }

                        async function updateStats() {
                            try {
                                const response = await fetch(`${'$'}{API_BASE}/proyectos`);
                                if (response.ok) {
                                    const proyectos = await response.json();
                                    document.getElementById('stat-proyectos').textContent = proyectos.length;
                                }
                            } catch (error) {
                                console.error('Error al actualizar estadísticas:', error);
                            }
                        }

                        async function crearProyecto() {
                            const nombre = document.getElementById('proyecto-nombre').value;
                            const descripcion = document.getElementById('proyecto-descripcion').value;

                            if (!nombre || !descripcion) {
                                showAlert('Por favor complete todos los campos', 'error');
                                return;
                            }
                            
                            try {
                                const response = await fetch(`${'$'}{API_BASE}/proyectos`, {
                                    method: 'POST',
                                    headers: { 'Content-Type': 'application/json' },
                                    body: JSON.stringify({ nombre: nombre, descripcion: descripcion })
                                });

                                if (response.ok) {
                                    document.getElementById('proyecto-nombre').value = '';
                                    document.getElementById('proyecto-descripcion').value = '';
                                    showAlert('¡Proyecto creado satisfactoriamente!');
                                    cargarProyectos();
                                    updateStats();
                                } else {
                                    showAlert('Error al crear proyecto', 'error');
                                }
                            } catch (error) {
                                showAlert('Error de conexión', 'error');
                                console.error('Error:', error);
                            }
                        }

                        async function cargarProyectos() {
                            try {
                                const response = await fetch(`${'$'}{API_BASE}/proyectos`);
                                if (response.ok) {
                                    const proyectos = await response.json();
                                    mostrarProyectos(proyectos);
                                }
                            } catch (error) {
                                console.error('Error al cargar proyectos:', error);
                            }
                        }

                        function mostrarProyectos(proyectos) {
                            const container = document.getElementById('lista-proyectos');
                            
                            if (proyectos.length === 0) {
                                container.innerHTML = `
                                    <div class="empty-state">
                                        <div class="empty-state-icon">📁</div>
                                        <div class="empty-state-text">No hay proyectos creados</div>
                                    </div>
                                `;
                                return;
                            }

                            container.innerHTML = proyectos.map(proyecto => `
                                <div class="card">
                                    <div class="card-header">
                                        <div class="card-title">${'$'}{proyecto.nombre}</div>
                                        <div class="card-id">#${'$'}{proyecto.id}</div>
                                    </div>
                                    <div class="card-description">${'$'}{proyecto.descripcion}</div>
                                </div>
                            `).join('');
                        }

                        // Inicializar la aplicación
                        window.addEventListener('DOMContentLoaded', () => {
                            cargarProyectos();
                            updateStats();
                        });
                    </script>
                </body>
                </html>
            """.trimIndent()
            Response(OK).header("Content-Type", "text/html").body(html)
        },

        // GET: Obtener todos los proyectos
        "/proyectos" bind GET to { request: Request ->
            val proyectos = gestorDeTareas.mostrarProyectos()
            val response = proyectosLens(proyectos, Response(OK))
            response.header("Content-Type", "application/json")
        },

        // POST: Crear un nuevo proyecto
        "/proyectos" bind POST to { request: Request ->
            try {
                val proyectoRequest = proyectoRequestLens(request)
                val proyecto = gestorDeTareas.crearProyecto(proyectoRequest.nombre, proyectoRequest.descripcion)
                val proyectoLens = Body.auto<Proyecto>().toLens()
                val response = proyectoLens(proyecto, Response(OK))
                response.header("Content-Type", "application/json")
            } catch (e: Exception) {
                Response(Status.BAD_REQUEST).body("Error al crear proyecto: ${e.message}")
            }
        }
    )

    val server = app.asServer(Jetty(8080)).start()
    println("Server started on http://localhost:8080")
    server.block()
}