stt = 0.05;
tFinal = 20;

s2 = tf(10, [1/25, 1/5, 1]);
info2 = stepinfo(s2, "SettlingTimeThreshold", stt);
[y2, t2] = step(s2, tFinal);

s2b = feedback(s2, 1);
info2b = stepinfo(s2b, "SettlingTimeThreshold", stt);
[y2b, t2b] = step(s2b, tFinal);

sp = stepplot(s2, s2b, tFinal);

% Input plot
yl = yline(1, "-");
yl.Color = [255/255, 152/255, 0/255];

% Legend
title(sp, "Partie II - 2nd ordre");
subtitle(sp, "Système M'2 bouclé (Comparaison)");
xlabel(sp, "t")

legend(sp, "s2(t)", "s'2(t)", "e(t)")

t = text(info2.SettlingTime + 0.25, y2(end)/1.05 - 0.25, "Rapidité à moyen terme");
t.Color = [0 0.447 0.741];
t = text(info2.PeakTime + 0.25, info2.Peak - 0.25, "Dépassement");
t.Color = [0 0.447 0.741];

t = text(info2b.SettlingTime + 0.25, y2b(end) - 0.25, "Rapidité à moyen terme");
t.Color = [0.851 0.325 0.098];
t = text(info2b.PeakTime + 0.25, info2b.Peak + 0.25, "Dépassement");
t.Color = [0.851 0.325 0.098];

% Characteritics
sp.InputVisible = "on";
sp.YLimits = [0,12];
sp.Characteristics.SettlingTime.Threshold = stt;
sp.Characteristics.SettlingTime.Visible = "on";
sp.Characteristics.PeakResponse.Visible = "on";

disp(table(...
    ["s2(t)"; "s'2(t)"], ...
    ["-2.5-4.3301i;-2.5+4.3301i"; "-2.5+16.3936i;-2.5-16.3936i"], ...
    [strjoin(string(pole(s2)), ","); strjoin(string(pole(s2b)), ",")], ...
    ["1.05"; "1.2"], ...
    [info2.SettlingTime; info2b.SettlingTime], ...
    ["9"; "0.09"], ...
    [abs(1 - y2(end)); abs(1 - y2b(end))], ...
    ["0.16"; "0.62"], ...
    [info2.Overshoot/100; info2b.Overshoot/100], ...
    VariableNames=["Tf"; "Est: P"; "Pôles"; "Est: ST"; "Settling time"; "Est: SE"; "Static error"; "Est: OS"; "Overshoot"] ...
));
