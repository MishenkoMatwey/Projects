$(document).on('click', function (e) {
    switch (e.target.id) {
        case  ("f-add"): {
            if ($('#inputName-page').css("display") === 'none') {
                $("#inputName-page").css({
                    "display": "block",
                    "top": e.pageY,
                    "left": e.pageX
                });
                $("#uploadButt").attr('id', "addFolder");
                $("#title").text("Создать папку");
                $("#name").placeholder = "Имя папки";
            }
            break;
        }
        case ("f-past"):
            if ($.cookie('cutNode') !== null) {
                $.ajax({
                    type: 'POST',
                    url: "/ajax/past",
                    data: {idNode: $.cookie('idFolder'), all: $.cookie('all')},
                    success: function (data) {
                        if (data !== "") {
                            $("#main-ul").html(data);
                        }
                    }
                });
            }
            break;
        case ("f-change"):
            if ($('#inputName-page').css("display") === 'none') {
                $("#inputName-page").css({
                    "display": "block",
                    "top": e.pageY,
                    "left": e.pageX
                });
                $("#uploadButt").attr('id', "changeFolder");
                $("#title").text("Изменить название папки");
                $("#name").placeholder = "Имя папки";
            }
            break;
        case ("f-delete"):
            deleteFolder(false);
            break;
        case ("f-delete-all"):
            deleteFolder(true);
            break;
        case ("f-cut"):
            cutFolder(false);
            break;
        case ("f-cut-all"):
            cutFolder(true);
            break;
    }
});

function deleteFolder(deleteAll) {
    if ($.cookie('idFolder') != null || deleteAll != null) {
        $.cookie($.cookie('idFolder'));
        $.ajax({
            type: 'POST',
            url: "/ajax/delete",
            data: {idNode: $.cookie('idFolder'), all: deleteAll},
            success: function (data) {
                if (data !== null) {
                    $("#main-ul").html(data);
                }
            }
        });
    }
}

function cutFolder(cutAll) {

    if ($.cookie('idFolder') != null || cutAll != null) {
        $.cookie('cutFolder', $.cookie('idFolder'));
        $.cookie('all', cutAll);
        $.ajax({
            type: 'POST',
            url: "/ajax/cut",
            data: {idNode: $.cookie('idFolder'), all: cutAll},
            success: function (data) {
                if (data !== null) {
                    $("#main-ul").html(data);
                }
            }
        });
    }
}

$(document).on("click", "#addFolder", function (e) {
    if ($('#name').val() !== '') {
        $.ajax({
            type: 'POST',
            url: "/ajax/add",
            data: {idParentNode: $.cookie('idFolder'), nodeForAdd: $("#name").val()},
            success: function (data) {
                if (data !== null) {
                    $("#main-ul").html(data);
                }
            }
        });
    }
    close();
});

$(document).on("click", "#changeFolder", function (e) {
    $.ajax({
        type: 'POST',
        url: "/ajax/change",
        data: {idNode: $.cookie('idFolder'), newName: $("#name").val()},
        success: function (data) {
            if (data !== null) {
                $("#main-ul").html(data);
            }
        }
    });
    close();
});

function close() {
    $("#changeFolder,#addFolder").attr('id', "uploadButt");
    $(".inputName-page").css({"display": "none"});
    $(".podlogka").css({"display": "none"});
    $("#name").val("");
}