/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
var contextPath = getContextPath();
var strCart = "";

function addSeparatorsNF(nStr, inD, outD, sep) {
    nStr += '';
    var dpos = nStr.indexOf(inD);
    var nStrEnd = '';
    if (dpos !== -1) {
        nStrEnd = outD + nStr.substring(dpos + 1, nStr.length);
        nStr = nStr.substring(0, dpos);
    }
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(nStr)) {
        nStr = nStr.replace(rgx, '$1' + sep + '$2');
    }
    return nStr + nStrEnd;
}

$(document).ready(function () {
    if ($('#back-to-top').length) {
        var scrollTrigger = 100,
                backToTop = function () {
                    var scrollTop = $(window).scrollTop();
                    if (scrollTop > scrollTrigger) {
                        $('#back-to-top').addClass('show');
                    } else {
                        $('#back-to-top').removeClass('show');
                    }
                };
        backToTop();
        $(window).on('scroll', function () {
            backToTop();
        });
        $('#back-to-top').on('click', function (e) {
            e.preventDefault();
            $('html,body').animate({
                scrollTop: 0
            }, 700);
        });
    }
});
$(document).ready(function () {
    var isSearchActive = false;
    var isUserActive = false;
    var isCategoryActive = false;

    $('#category').click(function () {
        if (isCategoryActive === false) {
            $('#category a span').css({
                "transform": "rotate(360deg)",
                "transition": "0.5s"
            });
            $('.category').slideDown(function () {
                isCategoryActive = true;
            });
        } else {
            $('#category a span').css({
                "transform": "rotate(180deg)",
                "transition": "0.5s"
            });
            $('.category').slideUp(function () {
                isCategoryActive = false;
            });
        }
    });
    $('.searchBtn img').mouseover(function () {
        $('.searchBtn').css({
            "-moz-border-radius": "30%",
            "-webkit-border-radius": "30%",
            "border-radius": "30%"
        });
    }).mouseleave(function () {
        $('.searchBtn').css({
            "-moz-border-radius": "0",
            "-webkit-border-radius": "0",
            "border-radius": "0"
        });
    });
    $('.userBtn img').mouseover(function () {
        $('.userBtn').css({
            "-moz-border-radius": "30%",
            "-webkit-border-radius": "30%",
            "border-radius": "30%"
        });
    }).mouseleave(function () {
        $('.userBtn').css({
            "-moz-border-radius": "0",
            "-webkit-border-radius": "0",
            "border-radius": "0"
        });
    });
    $('.searchBtn').click(function () {
        if (isUserActive === true) {
            $('.userOption').hide();
            cssUserBtnDefault();
            isUserActive = false;
        }
        if (isSearchActive === false) {
            $('#bgSearch').css('background', 'yellowgreen');
            $('.searchBtn').css('box-shadow', '0px 0px 5px yellowgreen inset');
            $('#searchFrm').toggle(function () {
                $('#searchVal').focus();
                isSearchActive = true;
            });
        } else {
            cssSearchBtnDefault();
            $('#searchFrm').toggle(function () {
                isSearchActive = false;
            });
        }
    });
    $('.userBtn').click(function () {
        if (isSearchActive === true) {
            $('#searchFrm').hide();
            cssSearchBtnDefault();
            isSearchActive = false;
        }
        if (isUserActive === false) {
            $('#bgUsOp').css('background', 'rgba(0,0,0,0.85)');
            $('.userBtn').css({
                "box-shadow": "0px 0px 5px yellowgreen inset",
                "margin": "0 133px 0 3px"
            });
            $('.userOption').toggle(function () {
                isUserActive = true;
            });
        } else {
            cssUserBtnDefault();
            $('.userOption').toggle(function () {
                isUserActive = false;
            });
        }
    });
    $('.searchBtn').mouseup(function () {
        return false;
    });
    $('.userBtn').mouseup(function () {
        return false;
    });
    $('#bgSearch').mouseup(function () {
        return false;
    });
    $('#bgUsOp').mouseup(function () {
        return false;
    });
    $(document).mouseup(function () {
        if (isSearchActive === true) {
            cssSearchBtnDefault();
            $('#searchFrm').hide(function () {
                isSearchActive = false;
            });
        }
        if (isUserActive === true) {
            cssUserBtnDefault();
            $('.userOption').hide(function () {
                isUserActive = false;
            });
        }
        if (isCategoryActive === true) {
            $('#category a span').css({
                "transform": "rotate(180deg)",
                "transition": "0.5s"
            });
            $('.category').slideUp(function () {
                isCategoryActive = false;
            });
        }
    });
    function cssSearchBtnDefault() {
        $('#bgSearch').css('background', 'none');
        $('.searchBtn').css('box-shadow', 'none');
        $('#searchVal').focusout();
    }
    function cssUserBtnDefault() {
        $('#bgUsOp').css('background', 'none');
        $('.userBtn').css({
            "box-shadow": "none",
            "margin": "0 3px"
        });
    }
});
$(document).ready(function () {
    $('.userOption #signup').click(function () {
        window.location = contextPath + "/user/signup.html";
    });
    $('.userOption #cruser').click(function () {
        var crUsMbName = $(this).attr("data-bind");
        window.location = contextPath + "/member/" + crUsMbName + "/infor.html";
    });
    $('.userOption #rdadmin').click(function () {
        window.location = contextPath + "/admin";
    });
    $('.userOption #cartsv').click(function () {
        var crUsMbName = $(this).attr("data-bind");
        window.location = contextPath + "/member/" + crUsMbName + "/cartsaved.html";
    });
    $('.userOption #ordersv').click(function () {
        var crUsMbName = $(this).attr("data-bind");
        window.location = contextPath + "/member/" + crUsMbName + "/ordersaved.html";
    });
    $('.userOption #login').click(function () {
        $('#mirrorbg').removeClass("hide");
        $('#mirrorbg').addClass("showlogin");
    });
    $('#lgnow').click(function () {
        $('#mirrorbg').removeClass("hide");
        $('#mirrorbg').addClass("showlogin");
    });
    $('#clostlogin').click(function () {
        $('#mirrorbg').removeClass("showlogin");
        $('#mirrorbg').addClass("hide");
        return false;
    });
});
//login
$(document).ready(function () {
    $("#lgFrm").submit(function (e) {
        $.ajax({
            type: "POST",
            url: contextPath + "/member/login",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: $("#lgFrm").serialize(),
            success: function (response) {
                if (response !== null) {
                    var crUrl = response.split("###")[0];
                    switch (response.split("###")[1]) {
                        case "MemberLogged":
                            window.location = crUrl;
                            break;
                        case "EmployeeLogged":
                            window.location = contextPath + "/admin";
                            break;
                        default :
                            $('#lgFrm h2').removeClass("hide");
                            break;
                    }
                } else {
                    $('#lgFrm h2').removeClass("hide");
                }
            }
        });

        e.preventDefault();
    });
});
//log out
$(document).ready(function () {
    $("#logout").click(function () {
        $.ajax({
            type: "POST",
            url: contextPath + "/member/logout",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: "crUrl=" + $(location).attr('href'),
            success: function (response) {
                if (response !== "Fail") {
                    window.location = response;
                }
            }
        });
    });
});

//manager log out
$("#lobtn").click(function (e) {
    $.ajax({
        type: "POST",
        url: contextPath + "/admin/logout",
        header: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (response) {
            if (response !== null && response !== "Fail") {
                $(location).attr("href", response);
            }
        }
    });
    e.preventDefault();
});
$(document).ready(function () {
    $('.newbook').slick({
        dots: false,
        infinite: true,
        speed: 500,
        fade: true,
        autoplay: true,
        autoplaySpeed: 5000,
        cssEase: 'linear'
    });
    $('#bookByWeek').slick({
        centerMode: true,
        centerPadding: '60px',
        slidesToShow: 3,
        responsive: [
            {
                breakpoint: 768,
                settings: {
                    arrows: false,
                    centerMode: true,
                    centerPadding: '40px',
                    slidesToShow: 3
                }
            },
            {
                breakpoint: 480,
                settings: {
                    arrows: false,
                    centerMode: true,
                    centerPadding: '40px',
                    slidesToShow: 1
                }
            }
        ]
    });
    $('#bookByMonth').slick({
        centerMode: true,
        centerPadding: '60px',
        slidesToShow: 3,
        responsive: [
            {
                breakpoint: 768,
                settings: {
                    arrows: false,
                    centerMode: true,
                    centerPadding: '40px',
                    slidesToShow: 3
                }
            },
            {
                breakpoint: 480,
                settings: {
                    arrows: false,
                    centerMode: true,
                    centerPadding: '40px',
                    slidesToShow: 1
                }
            }
        ]
    });
    $('#bookByYear').slick({
        centerMode: true,
        centerPadding: '60px',
        slidesToShow: 3,
        responsive: [
            {
                breakpoint: 768,
                settings: {
                    arrows: false,
                    centerMode: true,
                    centerPadding: '40px',
                    slidesToShow: 3
                }
            },
            {
                breakpoint: 480,
                settings: {
                    arrows: false,
                    centerMode: true,
                    centerPadding: '40px',
                    slidesToShow: 1
                }
            }
        ]
    });
    $('.newbook article p').each(function (i) {
        var len = $(this).text().length;
        if (len > 475) {
            $(this).text($(this).text().substr(0, 475) + ' ...');
        }
    });
    $('.bookdetails .artcl').each(function (i) {
        var artclHeight = $(this).find("p").outerHeight(true) + 100;
        $(this).css("height", artclHeight + "px");
    });
});

//order book
$(document).ready(function () {
    $('.odb').click(function () {
        var itemData = $(this).attr("data-bind");
        var dt = itemData.split("#");
        $.ajax({
            url: contextPath + "/crbookorder",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            type: "POST",
            data: "it=" + dt[0],
            success: function (response) {
                if (response !== null && response === "OK") {
                    if (dt[1] === "") {
                        window.location = contextPath + "/bookorder.html";
                    } else {
                        window.location = contextPath + "/member/" + dt[1] + "/bookorder.html";
                    }
                }
            },
            error: function (xhr, status, error) {
            }
        });

    });
});

//add item to cart
$(document).ready(function () {
    $('.addToCart').click(function () {
        var target = $(this);
        var itemData = $(this).attr("data-bind");
        $.ajax({
            url: contextPath + "/cart/addToCart",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            type: "POST",
            data: "item=" + itemData,
            success: function (response) {
                if (response !== null && response !== "") {
                    updateCartText(target, response, itemData);
                }
            },
            error: function (xhr, status, error) {
            }
        });
    });
});
function updateCartText(target, response, itemData) {
    var arr = response.split("#");
    $('.amount #amount').html("(" + arr[0] + ")");
    $('.totalPrice #totalPrice').html(addSeparatorsNF(arr[1], '.', '.', ','));
    target.parent().find("img").removeClass("hide");
    target.replaceWith("");
    $(".addToCart[data-bind='" + itemData + "']").parent().find("img").removeClass("hide");
    $(".addToCart[data-bind='" + itemData + "']").replaceWith("");
}
//// change remember password value
//function setValueLogin() {
//    var target = $('.login').find('input');
//    if (target.eq(2).is(":checked")) {
//        target.eq(3).val("1");
//    } else {
//        target.eq(3).val("0");
//    }
//}
//function setValueRegisterSc() {
//    var target = $('.registerSc form').find('input');
//    if (target.eq(2).is(":checked")) {
//        target.eq(3).val("1");
//    } else {
//        target.eq(3).val("0");
//    }
//}
//auto change price()
$(document).ready(function () {
    $('.quantity select').change(function () {
        var target = $(this);
        var qtt = target.val();
        $('#updateCart').removeClass("hide");
        $('#updateCart').removeAttr("disabled");
        $('#otherop').replaceWith("");
        var pr = target.parent().parent().find("td").eq(4).find("input").val();
        var crTtPr = parseFloat(qtt) * parseFloat(pr);
        target.parent().parent().find("td").eq(8).find("input").val(crTtPr);
        var nTtPr = 0;
        var fields = document.getElementsByName("totalPrice[]");
        for (var i = 0; i < fields.length; i++) {
            nTtPr += parseFloat(fields[i].value);
        }
        $('.ttPr input').val(parseFloat(nTtPr));
        strCart += pr = target.parent().parent().find("td").eq(5).find("input").val() + "@" + target.val() + "#";
    });
});
//update cart
$(document).ready(function () {
    $("#fcart #updateCart").click(function (e) {
        $.ajax({
            type: "POST",
            url: contextPath + "/cart/update",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: "strCart=" + strCart + "&nTtPr=" + $('.ttPr input').val(),
            success: function (response) {
                if (response === "updated") {
                    strCart = "";
                    $(location).attr("href", contextPath + "/cart.html");
                }
            }
        });
        e.preventDefault();
    });
});
//save cart - with customer member
$(document).ready(function () {
    $("#fcart #saveCart").click(function (e) {
        $.ajax({
            type: "POST",
            url: contextPath + "/cart/save",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: "",
            success: function (response) {
                if (response !== "unsaved") {
                    $(location).attr("href", contextPath + "/member/" + response + "/cartsaved.html");
                } else {
                    $('#fscart #saveerror').removeClass("hide");
                }
            }
        });
        e.preventDefault();
    });
});
//member order
$(document).ready(function () {
    $(".mbOrder").click(function (e) {
        var itemData = $(this).attr("data-bind");
        $.ajax({
            type: "POST",
            url: contextPath + "/member/memberorder",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: "idCartSave=" + itemData,
            success: function (response) {
                if (response !== null && response !== "cancelOrder") {
                    $(location).attr("href", contextPath + "/member/" + response + "/addressshipping.html");
                } else {
                    $('#fscartsv #ordererror').removeClass("hide");
                }
            }
        });
        e.preventDefault();
    });
});
//member delete cart saved
$(document).ready(function () {
    $(".deleteCsv").click(function (e) {
//        var isConfirmed = confirm("Delete confirm!");
//        if (isConfirmed === true) {
        var itemData = $(this).attr("data-bind");
        $.ajax({
            type: "POST",
            url: contextPath + "/member/deleteCsv",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: "idCartSave=" + itemData,
            success: function (response) {
                if (response !== null && response !== "cancelDelete") {
                    $(location).attr("href", contextPath + "/member/" + response + "/cartsaved.html");
                } else {
                    $('#fscartsv #deleteerror').removeClass("hide");
                }
            }
        });
        e.preventDefault();
//        }
    });
});
//auto change price order book
$(document).ready(function () {
    $('#addressShipping table select').change(function () {
        var target = $(this);
        var qtt = target.val();
        var pr = target.parent().parent().find("td").eq(4).find("input").val();
        var crTtPr = parseFloat(qtt) * parseFloat(pr);
        target.parent().parent().find("td").eq(8).find("input").val(crTtPr);
        target.parent().find("input").val(qtt);
    });
});
//member order book
$(document).ready(function () {
    $("#bodbmb").submit(function (e) {
        var crmb = $('#crcusmbun').val();
        $.ajax({
            type: "POST",
            url: contextPath + "/member/" + crmb + "/bookorder",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: $("#bodbmb").serialize(),
            success: function (response) {
                if (response !== null && response === "OK") {
                    $(location).attr("href", contextPath + "/member/" + crmb + "/ordersaved.html");
                } else {
                    $('#ordererror h2').removeClass("hide");
                }
            }
        });

        e.preventDefault();
    });
});
//customer order book
$(document).ready(function () {
    $('#otherop').click(function () {
        $('#odcart').slideDown(500);
//        $('#odcart').removeClass("hide");
    });

    $("#odcart").submit(function (e) {
        $.ajax({
            type: "POST",
            url: contextPath + "/cartorder",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: $("#odcart").serialize(),
            success: function (response) {
                if (response !== null && response === "OK") {
                    $(location).attr("href", contextPath + "/ordersuccessful.html");
                } else {
                    $('#ordererror h2').removeClass("hide");
                }
            }
        });

        e.preventDefault();
    });

    $("#bodbcs").submit(function (e) {
        $.ajax({
            type: "POST",
            url: contextPath + "/bookorder",
            header: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            data: $("#bodbcs").serialize(),
            success: function (response) {
                if (response !== null && response === "OK") {
                    $(location).attr("href", contextPath + "/ordersuccessful.html");
                } else {
                    $('#ordererror h2').removeClass("hide");
                }
            }
        });

        e.preventDefault();
    });
});