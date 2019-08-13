$(document).ready(function(){
	$("#ajax-btn-edit-pr").click(function(){
	    var idv = $("#id").attr('value')
		var namev = $("#name").val()
		var dateFromv = $("#dateFrom").val()
		var dateTov = $("#dateTo").val()
		var dateWhenv = $("#dateWhen").val()
		$.ajax({
			type: "PUT",
			contentType: "application/json",
			url: "/purchase",
			data: JSON.stringify({"id": idv,
			       "name": namev,
			       "dateFrom": dateFromv,
			       "dateTo": dateTov,
			       "dateWhen": dateWhenv
			       })
		})
		.done(function(){
		    location.href='/';
		});
	})

    $("#ajax-btn-edit-per").click(function(){
        var idv = $("#id").attr('value')
        var namev = $("#name").val()
        var dateLastv = $("#dateLast").val()
        var numdaysv = $("#numdays").val()
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/period",
            data: JSON.stringify({"id": idv,
                   "name": namev,
                   "dateLast": dateLastv,
                   "numdays": numdaysv
                   })
        })
        .done(function(){
            location.href='/periods';
        });
    })

    $("#ajax-btn-new-pr").click(function(){
        var namev = $("#name").val()
        var dateFromv = $("#dateFrom").val()
        var dateTov = $("#dateTo").val()
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/purchase",
            data: JSON.stringify({
                   "name": namev,
                   "dateFrom": dateFromv,
                   "dateTo": dateTov
                   })
        })
        .done(function(){
            location.href='/';
        });
    })

    $("#ajax-btn-new-per").click(function(){
        var namev = $("#name").val()
        var dateLastv = $("#dateLast").val()
        var numdaysv = $("#numdays").val()
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/period",
            data: JSON.stringify({
                   "name": namev,
                   "dateLast": dateLastv,
                   "numdays": numdaysv
                   })
        })
        .done(function(){
            location.href='/periods';
        });
    })

    $(".ajax-btn-rej-pr").click(function(){
        var idv = $(this).val()
        var arr = idv.split('-')

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/purchase/reject/"+arr[1],
            data: JSON.stringify({
                   })
        })
        .done(function(){
            if(arr[0] == "fcur"){
                location.href='/purchases/today';
            } else if(arr[0] == "cur"){
                location.href='/';
            }

        });
    })

    $(".ajax-btn-buy-pr").click(function(){
        var idv = $(this).val()
        var arr = idv.split('-')
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/purchase/buy/"+arr[1],
            data: JSON.stringify({
                   })
        })
        .done(function(){
            if(arr[0] == "fcur"){
                location.href='/purchases/today';
            } else if(arr[0] == "cur"){
                location.href='/';
            }

        });
    })

    $(".ajax-btn-exp-pr").click(function(){
        var idv = $(this).val()
        var arr = idv.split('-')
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: "/purchase/delete/"+arr[1],
            data: JSON.stringify({
                   })
        })
        .done(function(){
            location.href='/purchases/expired';
        });
    })

    $(".ajax-btn-exp-per").click(function(){
        var idv = $(this).val()
        var arr = idv.split('-')
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: "/period/delete/"+arr[1],
            data: JSON.stringify({
                   })
        })
        .done(function(){
            location.href='/periods';
        });
    })

    $(".ajax-btn-del-pr").click(function(){
        var idv = $(this).val()
        var arr = idv.split('-')
        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: "/purchase/delete/"+arr[1],
            data: JSON.stringify({
                   })
        })
        .done(function(){
            if(arr[0] == "fcur"){
                location.href='/purchases/today';
            } else if(arr[0] == "cur"){
                location.href='/';
            }

        });
    })
})