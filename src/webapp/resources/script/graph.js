const width = 3600;
const height = 3600; // TODO: remove hardcode
const R = width / 4.25;
const centerX = width / 2;
const centerY = height / 2;


function drawGraph() {
    const canvas = document.createElement("canvas");
    const ctx = canvas.getContext("2d");
    canvas.width = width;
    canvas.height = height;

    let r = document.querySelector("input[name$=\":r\"]:checked")?.value
    if (r == null) {
        r = 0
    }
    const scale = R / 5 * r;

    ctx.clearRect(0, 0, width, height);
    drawAxis(ctx);
    drawAreas(ctx, scale);

    const graphicImage = document.getElementById("graph-image");
    graphicImage.src = canvas.toDataURL("image/png");
}

function drawPoint(ctx, x, y, r, isInside) {
    const scale = R / r;
    const radius = R / 100;
    const px = centerX + x * scale;
    const py = centerY - y * scale;

    ctx.beginPath();
    ctx.arc(px, py, radius, 0, 2 * Math.PI);
    ctx.fillStyle = isInside ? "lime" : "red";
    ctx.fill();
}

function drawAxis(ctx) {
    const arrowLength = 6;
    const divLineLength = 6;
    const gap = 6;
    ctx.font = "bold 1rem monospace";
    ctx.fillStyle = "rgba(255, 255, 255, 1)";

    // X-axis
    ctx.beginPath();
    ctx.moveTo(0, centerY);
    ctx.lineTo(centerX * 2, centerY);
    ctx.fillText("X", centerX * 2 - 8, centerY + 20);
    // Y-axis
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX, centerY * 2);
    ctx.fillText("Y", centerX + 10, 10);
    ctx.strokeStyle = "white";
    ctx.stroke()

    // X-axis arrow
    ctx.beginPath();
    ctx.lineTo(centerX * 2 - arrowLength, centerY + arrowLength);
    ctx.lineTo(centerX * 2 - arrowLength, centerY - arrowLength);
    ctx.lineTo(centerX * 2, centerY);
    ctx.fill();
    // Y-axis arrow
    ctx.beginPath();
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX + arrowLength, arrowLength);
    ctx.lineTo(centerX - arrowLength, arrowLength);
    ctx.fill();
}

function drawAreas(ctx, scaledR) {
    ctx.fillStyle = "rgba(250, 250, 250, 0.2)";

    ctx.beginPath();
    ctx.moveTo(centerX + 0.2 * scaledR, centerY + scaledR);
    ctx.lineTo(centerX + 0.2 * scaledR, centerY - scaledR);
    ctx.lineTo(centerX + 0.5 * scaledR, centerY - scaledR);
    ctx.lineTo(centerX + 0.1 * scaledR, centerY - (-0.5 * 0.1 + 1.25) * scaledR);
    ctx.lineTo(centerX + 0.1 * scaledR, centerY - 1.9 * scaledR);
    ctx.arc(centerX, centerY - 1.9 * scaledR, scaledR / 10, 0, Math.PI, true);
    ctx.lineTo(centerX - 0.1 * scaledR, centerY - 1.2 * scaledR);
    ctx.lineTo(centerX - 0.5 * scaledR, centerY - scaledR);
    ctx.lineTo(centerX - 0.2 * scaledR, centerY - scaledR);
    ctx.lineTo(centerX - 0.2 * scaledR, centerY + scaledR);
    ctx.lineTo(centerX, centerY + 2 * scaledR);
    ctx.lineTo(centerX + 0.2 * scaledR, centerY + scaledR);
    ctx.stroke();
    ctx.fill();
}

window.addEventListener("load", () => drawGraph());

// canvas.addEventListener("click", (e) => {
//     const rInput = document.getElementById("r");
//
//     const r = parseFloat(rInput.value);
//     if (!r || isNaN(r) || r === 0) {
//         errorMsg.textContent = "Please set a valid R before clicking on the graph.";
//         errorMsg.hidden = false;
//         return;
//     }
//
//     const scale = R / r;
//     const rect = canvas.getBoundingClientRect();
//     const mouseX = e.clientX - rect.left;
//     const mouseY = e.clientY - rect.top;
//
//     const x = (mouseX - centerX) / scale;
//     const y = (centerY - mouseY) / scale;
//
//     const point = {x, y, r};
//     sendRequest(point);
// });