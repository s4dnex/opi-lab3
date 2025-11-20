const width = 1200;
const height = 1200;
const R = width / 4.25;
const centerX = width / 2;
const centerY = height / 2;
const scale = R / 5;

function drawGraph() {
    const canvas = document.createElement("canvas");
    const ctx = canvas.getContext("2d");
    canvas.width = width;
    canvas.height = height;

    let r = document.querySelector("input[name$=\":r\"]:checked")?.value
    if (r == null) {
        r = 0
    }
    const graphScale = R / 5 * r;

    ctx.clearRect(0, 0, width, height);
    drawAxis(ctx);
    drawAreas(ctx, graphScale);
    drawPointsFromTable(ctx, r);

    const graphicImage = document.getElementById("graph-image");
    graphicImage.src = canvas.toDataURL("image/png");
}

function drawPointsFromTable(ctx, r) {
    const table = document.querySelector('#hidden-points-table');
    const points = [];

    if (table) {
        const rows = (table.tBodies.length ? table.tBodies[0].rows : table.rows);

        for (let i = 0; i < rows.length; i++) {
            const cells = rows[i].cells;
            if (!cells || cells.length === 0) continue;

            const x = parseFloat(cells[0].innerText);
            const y = parseFloat(cells[1].innerText);
            const r = parseInt(cells[2].innerText);
            const statusText = cells[4] ? cells[4].innerText.trim().toUpperCase() : "";
            const hit = statusText.indexOf('HIT') !== -1;

            if (!Number.isNaN(x) && !Number.isNaN(y)) {
                points.push({x: x, y: y, r: r, hit: hit});
            }
        }
    } else {
        console.warn("Points table not found.");
    }

    for (const p of points) {
        if (r != p.r) continue;
        drawPoint(ctx, p.x, p.y, p.hit)
    }
}

function drawPoint(ctx, x, y, isInside) {
    const radius = scale / 25;
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
    ctx.lineTo(width, centerY);
    ctx.fillText("X", width - 8, centerY + 20);
    // Y-axis
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX, height);
    ctx.fillText("Y", centerX + 10, 10);
    ctx.strokeStyle = "white";
    ctx.stroke()

    // X-axis arrow
    ctx.beginPath();
    ctx.lineTo(width - arrowLength, centerY + arrowLength);
    ctx.lineTo(width - arrowLength, centerY - arrowLength);
    ctx.lineTo(width, centerY);
    ctx.fill();
    // Y-axis arrow
    ctx.beginPath();
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX + arrowLength, arrowLength);
    ctx.lineTo(centerX - arrowLength, arrowLength);
    ctx.fill();

    for (let i = -10; i <= 10; i++) {
        if (i === 0) continue;

        const x = centerX + i * R / 5;

        ctx.beginPath();
        ctx.moveTo(x, centerY - divLineLength / 2);
        ctx.lineTo(x, centerY + divLineLength / 2);
        ctx.stroke();

        // ctx.fillText(i.toString(), x - gap, centerY + 20);
    }

    for (let i = -10; i <= 10; i++) {
        if (i === 0) continue;

        const y = centerY - i * R / 5;

        ctx.beginPath();
        ctx.moveTo(centerX - divLineLength / 2, y);
        ctx.lineTo(centerX + divLineLength / 2, y);
        ctx.stroke();

        // ctx.fillText(i.toString(), centerX - 25, y + gap);
    }

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

function onGraphClick(event) {
    const img = event.currentTarget;
    const rect = img.getBoundingClientRect();

    const mouseX = event.clientX - rect.left;
    const mouseY = event.clientY - rect.top;

    const scaleX = (img.naturalWidth || width) / rect.width;
    const scaleY = (img.naturalHeight || height) / rect.height;

    const x = (mouseX * scaleX - centerX) / scale;
    const y = (centerY - mouseY * scaleY) / scale;

    const xInput = document.getElementById("input-form:x");
    const yInput = document.getElementById("input-form:y");

    xInput.value = x;
    yInput.value = y;

    const clickCheckbox = PF("clickCheckboxVar");
    clickCheckbox.check();

    submitClickPoint();
}

function uncheckClick() {
    const clickCheckbox = PF("clickCheckboxVar");
    clickCheckbox.uncheck();
}