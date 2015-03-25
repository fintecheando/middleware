Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.draw.*',
    'Ext.chart.*',
    'Ext.panel.*',
    'Ext.layout.container.Border'
]);

    

Ext.onReady(function(){   
    
    // Set up a model to use in our Store
 Ext.define('User', {
     extend: 'Ext.data.Model',
     fields: [
         {name: 'name', type: 'string'},         
         {name: 'plannedamt',       type: 'int'},
         {name: 'committedamt',       type: 'int'}
     ]
 });

 var myStore = Ext.create('Ext.data.Store', {
     model: 'User',
     proxy: {
            type: 'ajax',
            url: 'findProject.action?CProjectId=102',
            reader: {
                type: 'json',                
                rootProperty: 'project'
            }
        },        
        //alternatively, a Ext.data.Model name can be given (see Ext.data.Store for an example)
        fields: ['name', 'plannedamt','committedamt'],
     autoLoad: true
 });
    
    Ext.create('Ext.Container', {
    renderTo: Ext.getBody(),
    width:600,
    height:1000,
    layout: 'fit',
    items: [
        {
            xtype: 'chart',
            flipXY: true,
            store: myStore,  

            //set legend configuration
            legend: {
                docked: 'right'
            },

            //define the x and y-axis configuration.
            axes: [
                {
                    type: 'numeric',
                    position: 'bottom',
                    grid: true,
                    title: 'Presupuesto',
                    minimum: 0
                    
                },
                {
                    type: 'category',
                    position: 'left',
                    title: 'Obra'
                }
            ],

            //define the actual bar series.
            series: [
                {
                    type: 'bar',
                    xField: 'name',
                    yField: ['plannedamt', 'committedamt'],
                    title: ['Presupuestado', 'Ejercido'],
                    axis: 'bottom',
                    // Cycles the green and blue fill mode over 2008 and 2009
                    // subStyle parameters also override style parameters
                    subStyle: {
                        fill: ["#115fa6", "#94ae0a"]
                    }
                }
            ]
        }
    ]
});

});