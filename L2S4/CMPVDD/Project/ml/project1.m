stt = 0.05;
tFinal = 20;

s0 = tf(10, [2, 1]);
info0 = stepinfo(s0, "SettlingTimeThreshold", stt);
[y0, t0] = step(s0, tFinal);

s0b = feedback(s0, 1);
info0b = stepinfo(s0b, "SettlingTimeThreshold", stt);
[y0b, t0b] = step(s0b, tFinal);

sp = stepplot(s0, s0b, tFinal);

% Input plot
yl = yline(1, "-");
yl.Color = [255/255, 152/255, 0/255];

% Legend
title(sp, "Partie I - 1er ordre");
subtitle(sp, "Système bouclé (Comparaison)");
xlabel(sp, "t")

legend(sp, "s(t)", "s'(t)", "e(t)")

t = text(info0.SettlingTime + 0.25, y0(end)/1.05 - 0.25, "Rapidité à moyen terme");
t.Color = [0 0.447 0.741];
t = text(info0b.SettlingTime + 0.25, y0b(end)/1.05 - 0.25, "Rapidité à moyen terme");
t.Color = [0.851 0.325 0.098];

% Characteritics
sp.InputVisible = "on";
sp.YLimits = [0,11.5];
sp.Characteristics.SettlingTime.Threshold = stt;
sp.Characteristics.SettlingTime.Visible = "on";

disp(table(...
    ["s(t)"; "s'(t)"], ...
    ["-1/2"; "-11/2"], ...
    [pole(s0); pole(s0b)], ...
    ["6"; "6/11"], ...
    [info0.SettlingTime; info0b.SettlingTime], ...
    ["9"; "1/11"], ...
    [abs(1 - y0(end)); abs(1 - y0b(end))], ...
    VariableNames=["Tf"; "Est: P"; "Pôles"; "Est: ST"; "Settling time"; "Est: SE"; "Static error"] ...
));