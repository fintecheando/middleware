Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.draw.*',
    'Ext.chart.*',
    'Ext.panel.*',
    'Ext.layout.container.Border'
]);

Ext.onReady(function(){    
    
    var myDataStore = Ext.create('Ext.data.JsonStore', {
            fields: ['os', 'data1' ],
            data: [
                { os: 'Android', data1: 68.3 },
                { os: 'iOS', data1: 17.9 },
                { os: 'Windows Phone', data1: 10.2 },
                { os: 'BlackBerry', data1: 1.7 },
                { os: 'Others', data1: 1.9 }
            ]
        });

    Ext.create('Ext.Container', {
    renderTo: Ext.getBody(),
    width: 500,
    height: 500,
    layout: 'fit',
    items: [
        {
            xtype: 'polar',
            store: {
                fields: ['name', 'g1', 'g2'],
                data: [
                    {"name": "Item-0", "g1": 18.34,"g2": 0.04},
                    {"name": "Item-1", "g1": 2.67, "g2": 14.87},
                    {"name": "Item-2", "g1": 1.90, "g2": 5.72},
                    {"name": "Item-3", "g1": 21.37,"g2": 2.13},
                    {"name": "Item-4", "g1": 2.67, "g2": 8.53},
                    {"name": "Item-5", "g1": 18.22,"g2": 4.62}
                ]
            },

            interactions: ['rotate'],

            //configure the legend.
            legend: {
                docked: 'bottom'
            },

            //describe the actual pie series.
            series: [
                {
                    type: 'pie',
                    xField: 'g1',
                    label: {
                        field: 'name',
                        display: 'rotate'
                    },
                    donut: 25,
                    style: {
                        miterLimit: 10,
                        lineCap: 'miter',
                        lineWidth: 2
                    }
                }
            ]
        }
    ]
});

});