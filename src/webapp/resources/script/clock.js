window.addEventListener("load", stopClock)
window.addEventListener("load", repaintClock)

setInterval(function () {
    updateClock();
}, 11 * 1000)

function stopClock() {
    PF("clockVar").stop();
}

function repaintClock() {
    PF("clockVar").pin.attr("fill", "black");
}