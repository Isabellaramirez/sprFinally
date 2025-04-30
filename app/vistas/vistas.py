import datetime
from flask_restful import Resource
from flask import request 
from ..modelo import db, Usuario, UsuarioSchema, Mensajes, MensajesSchema, Categoria, CategoriasSchema, Rol
from flask_jwt_extended import get_jwt_identity, jwt_required, create_access_token
from cloudinary.uploader import upload
usuario_schema = UsuarioSchema()
mensajes_schema = MensajesSchema

class VistaContratista(Resource):
    @jwt_required()
    def get(self, cedula):

        #token_cedula = get_jwt_identity()
        #obtener la cedula del usuario desde el token 
        #cedula = get_jwt_identity() #lo comentareo ya que la cedula se esta llamando como un parametro en el metodo asi que no es necesario
        contratista = Usuario.query.get_or_404(cedula)
        if contratista is None :
            return {'mensaje': 'contratista no encontrado'}, 404
        contratista_data = {
            "nombres":contratista.nombres ,
            "apellidos":contratista.apellidos ,
            "celular":contratista.celular ,
            "direccion":contratista.direccion  ,
            "correo":contratista.correo ,
            "fecha_nacimiento":contratista.fecha_nacimiento.strftime('%Y-%m-%d')  ,
            "foto": contratista.foto
             }
        
        return contratista_data, 200 #esto es para traer todo lo insertado 


class VistaPrestador(Resource):
    @jwt_required()
    def get(self, cedula):
        prestador = Usuario.query.get_or_404(cedula)
        if prestador is None:
            return { 'mensaje': 'prestador no encontrado'}, 404
        prestador_data = {
            "nombres" : prestador.nombres,
            "apellidos":prestador.apellidos ,
            "celular":prestador.celular ,
            "direccion":prestador.direccion  ,
            "titulos_uni": prestador.titulos_uni,
            "descripcion": prestador.descripcion,
            "correo":prestador.correo ,
            "fecha_nacimiento":prestador.fecha_nacimiento.strftime('%Y-%m-%d')  ,
            "foto": prestador.foto

        }
        return prestador_data,200

   
class VistaSignIn(Resource):    
    def post(self):
        print("\n=== PETICIÓN RECIBIDA ===")
        print("Headers:", request.headers)
        print("Form data:", request.form)
        print("Files:", request.files)
        print("Raw request data:", request.data)
        print("Request form keys:", request.form.keys())
        print("Request values:", request.values)
        
        # Manejo de la imagen
        archivo = request.files.get('foto')
        url_imagen = None 
        if archivo:
            resultado = upload(archivo)
            url_imagen = resultado.get('secure_url')
        
        # Validar rol
        id_rol = request.form.get('id_rol')
        if not id_rol:
            return {'mensaje': 'El rol es obligatorio'}, 400
        
        rol = Rol.query.get(id_rol)
        if not rol:
            return {'mensaje': f'El rol con id {id_rol} no existe'}, 404
        
        
        try:
            # Crear nuevo usuario
            nuevo_usuario = Usuario(
                cedula=request.form.get('cedula'),
                nombres=request.form.get('nombres'),
                apellidos=request.form.get('apellidos'),            
                celular=request.form.get('celular'),         
                direccion=request.form.get('direccion'),             
                contrasena=request.form.get('contrasena'),             
                titulos_uni=request.form.get('titulos_uni'),             
                descripcion=request.form.get('descripcion'),             
                correo=request.form.get('correo'),
                fecha_nacimiento = request.form.get('fecha_nacimiento'),
                foto=url_imagen,
                rol_id=id_rol
            )
            
            # Manejo de categorías (modificado)
            categorias_str = request.form.get('categoria', '')
            categoria_ids = [int(id) for id in categorias_str.split(',') if id.isdigit()]
            asociar_categorias = []
            for cat_id in categoria_ids:
                categoria = Categoria.query.get(cat_id)
                if categoria:
                    asociar_categorias.append(categoria)

            nuevo_usuario.categorias.extend(asociar_categorias)
            
            db.session.add(nuevo_usuario)
            db.session.commit()
            
            return usuario_schema.dump(nuevo_usuario), 201
            
        except Exception as e:
            db.session.rollback()
            print(f"Error al crear usuario: {str(e)}")
            return {'mensaje': f'Error al crear usuario: {str(e)}'}, 500


class VistaLogin(Resource):
    
    def post(self):
        print("petición de login recibida")
        print(f"Datos recibidos: {request.json}")
        u_correo = request.json["correo"]    
        u_contrasena = request.json["contrasena"]
        print(f"Correo: {u_correo}, Contraseña: {u_contrasena}")
        usuario = Usuario.query.filter_by(correo = u_correo).first()
        print(f"Usuario encontrado: {usuario}" if usuario else "Usuario no encontrado")
        if usuario and usuario.verificar_contrasena(u_contrasena):
            token_de_acceso = create_access_token(identity=str(usuario.cedula)) #se generaq el token
            print(f"token generado: {token_de_acceso}")
            return { 'mensaje' : 'inicio de sesion exitoso', "rol": usuario.rol_id, 'token_de_acceso': token_de_acceso}, 200
        else:
            print("Credenciales invalidas")
            return {'mensaje' : 'nombre de usuario o contraseña incorrectos, por favor intente de nuevo'}, 401 
        
    @jwt_required()
    def put(self, cedula):

        usuario = Usuario.query.get_or_404(cedula) #busca el usuario
        archivo = request.files.get('foto')
        if archivo:
            resultado =upload(archivo)
            url_imagen = resultado.get('secure_url')
            usuario.foto = url_imagen
        usuario.nombres = request.form.get('nombres', usuario.nombres)
        usuario.apellidos = request.form.get('apellidos', usuario.apellidos)
        usuario.celular = request.form.get('celular', usuario.celular)
        usuario.direccion = request.form.get('direccion', usuario.direccion)
        usuario.contrasena = request.form.get('contrasena', usuario.contrasena)
        usuario.titulos_uni = request.form.get('titulos_uni', usuario.titulos_uni)
        usuario.descripcion = request.form.get('descripcion', usuario.descripcion)
        usuario.correo = request.form.get('correo', usuario.correo)
        usuario.fecha_nacimiento = request.form.get('fecha_nacimiento', usuario.fecha_nacimiento)
        usuario.foto = url_imagen
        
        db.session.commit()
        return usuario_schema.dump(usuario), 200
    @jwt_required()   
    def delete(self, cedula):
        usuario = Usuario.query.get_or_404(cedula)
        db.session.delete(usuario)
        db.session.commit()
        return 'usuario eliminado exitosamente', 204
    
    
class Vista_Mensajeria(Resource):
    #ruta publica no necesita proteccion
    def get(self):
        return mensajes_schema.dump(Mensajes.query.all()), 200
    @jwt_required()
    def post(self):
        nuevo_mensaje = Mensajes(mensajes=request.json['mensajes'])
        db.session.add(nuevo_mensaje)
        return mensajes_schema.dump(nuevo_mensaje), 201