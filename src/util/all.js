function autochange() {
  var width = $(window).width();
  console.log("window_width  "+width)
  var headicon_width = width * 0.05 + 'px';
  console.log("lalalalala");
  if (width > 992) {
    $(".login-head").css("width", headicon_width);
  } else {
    $(".login-head").css("width", "50px");
  }
}


export default function all() {
  autochange();
  $(window).resize(autochange);
}
