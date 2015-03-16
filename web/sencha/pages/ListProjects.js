Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('Ext.ux', 'sencha/ux');
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.ux.form.SearchField'
]);

Ext.onReady(function(){
    Ext.define('ListProject', {
        extend: 'Ext.data.Model',
        fields: [{
            name: 'name',
            mapping: 'name'
        }, {
            name: 'description',
            mapping: 'description'
        }
        ],
        idProperty: 'CProjectId'
    });

    // create the Data Store
    var store = Ext.create('Ext.data.BufferedStore', {
        id: 'store',
        model: 'ListProject',
        
        // The topics-remote.php script appears to be hardcoded to use 50, and ignores this parameter, so we
        // are forced to use 50 here instead of a possibly more efficient value.
        pageSize: 50,

        // This web service seems slow, so keep lots of data in the pipeline ahead!
        leadingBufferZone: 1000,
        proxy: {
            // load using script tags for cross domain, if the data in on the same domain as
            // this page, an HttpProxy would be better
            type: 'jsonp',
            url: 'listAllProjects.action',
            reader: {
                rootProperty: 'projects',
                totalProperty: 'totalCount'
            },
            // sends single sort as multi parameter
            simpleSortMode: true,
            
            // Parameter name to send filtering information in
            filterParam: 'query',

            // The PHP script just use query=<whatever>
            encodeFilters: function(filters) {
                return filters[0].value;
            }
        },
        listeners: {
            totalcountchange: onStoreSizeChange
        },
        remoteFilter: true,
        autoLoad: true
    });
    
    function onStoreSizeChange() {
        grid.down('#status').update({count: store.getTotalCount()});
    }

    function renderTopic(value, p, record) {
        return Ext.String.format(
            '<a href="ProjectDetailedReport.action?CProjectId={1}" target="_blank">{0}</a>',
            value,
            record.getId()
        );
    }

    var grid = Ext.create('Ext.grid.Panel', {
        width: 700,
        height: 470,
        collapsible: false,
        title: 'Proyectos',
        store: store,
        loadMask: true,
        dockedItems: [{
            dock: 'top',
            xtype: 'toolbar',
            items: [{
                width: 400,
                fieldLabel: 'Buscar',
                labelWidth: 50,
                xtype: 'searchfield',
                store: store
            }, '->', {
                xtype: 'component',
                itemId: 'status',
                tpl: 'Elementos encontrados: {count}',
                style: 'margin-right:5px'
            }]
        }],
        selModel: {
            pruneRemoved: false
        },
        multiSelect: true,
        viewConfig: {
            trackOver: false,
            emptyText: '<h1 style="margin:20px">No matching results</h1>'
        },
        // grid columns
        columns:[{
            xtype: 'rownumberer',
            width: 50,
            sortable: false
        },{
            tdCls: 'x-grid-cell-topic',
            text: "Nombre",
            dataIndex: 'name',
            flex: 1,
            renderer: renderTopic,
            sortable: false
        },{
            text: "Descripcion",
            dataIndex: 'description',
            align: 'left',
            width: 300,
            sortable: false
        }],
        renderTo: Ext.getBody()
    });
});