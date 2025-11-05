window.addEventListener("load", stopClock)

setInterval(function () {
    updateClock();
}, 11 * 1000)

function stopClock() {
    PF("clockVar").stop();
}