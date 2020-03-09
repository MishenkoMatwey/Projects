$(document).on('change', '#DownloadFile', function () {
    let filename = this.files[0];
    let reader = new FileReader();
    reader.readAsText(filename);

    reader.onload = function () {
        $.ajax({
            type: 'POST',
            url: "/ajax/loadingFromFile",
            data: {fileText: reader.result},
            success: function (data) {
                if (data !== null) {
                    $("#main-ul").html(data);
                }
            }
        });
    };
});


$(document).on('click', '.treeview', function () {
    if ($('#main-ul').html().trim() === '' &&
        $("#downloadDB-page").css("display") === 'none' &&
        $(".inputName-page").css("display") === 'none') {
        $.ajax({
            type: 'POST',
            url: "/ajax/add",
            data: {nodeForAdd: "hello world"},
            success: function (data) {
                if (data !== null) {
                    $("#main-ul").html(data);
                }
            }
        });
    }
});

$(document).on('click', function (e) {

    if (e.target.id === "DownloadDB" && $(".inputName-page").css("display") === 'none') {
        $.ajax({
            type: 'GET',
            url: "ajax/loadingFromDB",
            success: function (data) {
                if (data !== null) {
                   let names=data.split(" ").map(function (el) {
                       return  "<h4>"+el+"</h4>"
                    });
                    $("#downloadDB-page").html(names).css({
                        "top": 0,
                        "right": -100,
                        "display": "block"
                    });
                }
            }
        });
    } else if (e.target.id === "UploadDB" && $("#downloadDB-page").css("display") === 'none') {
        if ($("#inputName-page").css("display") === 'none') {
            $(".inputName-page").css({
                "display": "block",
                "top": e.pageY,
                "left": e.pageX
            });
            $(".podlogka").css({"display": "block"});
        }
    } else {
        $("#downloadDB-page,.options").css({"display": "none"});
    }
});

$(document).on('click', 'h4', function (e) {
    $.ajax({
        type: 'POST',
        url: "/ajax/loadingFromDB",
        data: {nameTree: this.textContent},
        success: function (data) {
            if (data !== null) {
                $("#main-ul").html(data);
            }
        }
    });
});
$(document).on("click", "#uploadButt",function (e) {
    $.ajax({
        type: 'POST',
        url: "/ajax/saveToDB",
        data: {nameTree: $("#name").val()},
    });
    $(".inputName-page").css({"display": "none"});
    $(".podlogka").css({"display": "none"});
    $("#name").val("");
});

function changeSelectedFolder(idFolder) {
    if ($.cookie('idSelectedFolder') !== null) {
        $(`#${$.cookie('idSelectedFolder')}`).attr("src", "media/folder.png");
    }
    $.cookie('idSelectedFolder', idFolder);
    $(`#${idFolder}`).attr("src", "media/mark_folder.png");
}

document.addEventListener('contextmenu', function (e) {
    e.preventDefault();
}, false);

document.addEventListener("mousedown", function (e) {
    if (e.target.nodeName === "IMG") {
        if (e.which === 1) {
            changeSelectedFolder(e.target.id)
        } else if (e.which === 3) {
            $(".options").css({
                "display": "block",
                "left": e.pageX,
                "top": e.pageY
            });
            $.cookie('idFolder', e.target.id);
        }
    }
});


$(document).on('click', '.sc', function (e) {
    changeSelectedFolder(e.target.parentNode.firstChild.id);
    if (this.innerHTML.charCodeAt(0) === 9658) {
        let i = document.createElement('img');
        i.src = "static/img/loader.gif";
        i.className = 'loader';
        e.target.parentNode.appendChild(i);
        setTimeout(() => {
            this.innerHTML = '&#9660;';
            this.parentNode.parentNode.parentNode.className = '';
            e.target.parentNode.removeChild(i);
        }, 2000);
    } else {
        this.innerHTML = '&#9658;';
        this.parentNode.parentNode.parentNode.className = 'cl';
    }
});

