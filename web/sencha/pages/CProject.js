Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.panel.*',
    'Ext.layout.container.Border'
]);

Ext.onReady(function(){
    Ext.define('CProject',{
        extend: 'Ext.data.Model',
        proxy: {
            type: 'ajax',
            reader: 'json'
        },
        fields: [
        {name: 'name',  type: 'string'},
        {name: 'description',  type: 'string'}
    ]
    });

    // create the Data Store
    var store = new Ext.data.JsonStore({
    // store configs
    model: 'CProject',

        proxy: {
            type: 'ajax',
            url: 'listAllCProjects.action',
            reader: {
                type: 'json',
                rootProperty: 'projects'
            }
        },

        //alternatively, a Ext.data.Model name can be given (see Ext.data.Store for an example)
        fields: ['name', 'description']
    });

    // create the grid
    var grid = Ext.create('Ext.grid.Panel', {
        bufferedRenderer: false,
        store: store,
        columns: [
            {text: "Name", width: 120, dataIndex: 'name', sortable: true},
            {text: "Description", flex: 1, dataIndex: 'description', sortable: true}
            
        ],
        forceFit: true,
        height:210,
        split: true,
        region: 'north'
    });
        
    // define a template to use for the detail view
    var bookTplMarkup = [        
        'Name: {name}<br/>',
        'Description: {description}<br/>'
    ];
    var bookTpl = Ext.create('Ext.Template', bookTplMarkup);

    Ext.create('Ext.Panel', {
        renderTo: 'binding-example',
        frame: true,
        title: 'Project List',
        width: 580,
        height: 400,
        layout: 'border',
        items: [
            grid, {
                id: 'detailPanel',
                region: 'center',
                bodyPadding: 7,
                bodyStyle: "background: #ffffff;",
                html: 'Please select a project to see additional details.'
        }]
    });
    
    // update panel body on selection change
    grid.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
        if (selectedRecord.length) {
            var detailPanel = Ext.getCmp('detailPanel');
            detailPanel.update(bookTpl.apply(selectedRecord[0].data));
        }
    });

    store.load();
});