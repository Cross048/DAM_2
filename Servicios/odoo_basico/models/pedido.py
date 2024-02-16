from odoo import models, fields, api

class pedido(models.Model):
    _name = 'odoo_basico.pedido'
    _description = 'exemplo de pedido'
    _sql_constraints = [('nomeUnico', 'unique(name)', 'O Identificador de Pedido xa existe na Base de Datos')]
    _order = "name asc"

    name = fields.Char(required=True, size=20, string="Identificador de Pedido:")
    # Os campos One2many Non se almacenan na BD
    lineapedido_ids = fields.One2many("odoo_basico.lineapedido",'pedido_id')

    def creaRexistroInformacion(self):
        creado_id = self.env['odoo_basico.informacion'].create({'name': 'Creado dende pedido'})
        creado_id.descripcion = "Creado dende o modelo pedido"
        creado_id.autorizado = False

    def actualizaRexistroInformacion(self):
        informacion_id = self.env['odoo_basico.informacion'].search([('name', '=', 'Creado dende pedido')])
        if informacion_id:
            informacion_id.name = "Actualizado ..."
            informacion_id.descripcion = "Actualizado dende o modelo pedido"
            informacion_id.sexo_traducido = "Mujer"