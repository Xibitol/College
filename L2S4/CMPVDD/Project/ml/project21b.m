stt = 0.05;
tFinal = 20;

s1 = zpk([], [-1/2 -99/2], 250);
info1 = stepinfo(s1, "SettlingTimeThreshold", stt);
[y1, t1] = step(s1, tFinal);

s1b = feedback(s1, 1);
info1b = stepinfo(s1b, "SettlingTimeThreshold", stt);
[y1b, t1b] = step(s1b, tFinal);

sp = stepplot(s1, s1b, tFinal);

% Input plot
yl = yline(1, "-");
yl.Color = [255/255, 152/255, 0/255];

% Legend
title(sp, "Partie II - 2nd ordre");
subtitle(sp, "Système M'1 bouclé (Comparaison)");
xlabel(sp, "t")

legend(sp, "s1(t)", "s'1(t)", "e(t)")

t = text(info1.SettlingTime + 0.25, y1(end)/1.05 - 0.25, "Rapidité à moyen terme");
t.Color = [0 0.447 0.741];

t = text(info1b.SettlingTime + 0.25, y1b(end) - 0.25, "Rapidité à moyen terme");
t.Color = [0.851 0.325 0.098];

% Characteritics
sp.InputVisible = "on";
sp.YLimits = [0,13];
sp.Characteristics.SettlingTime.Threshold = stt;
sp.Characteristics.SettlingTime.Visible = "on";

disp(table(...
    ["s1(t)"; "s'1(t)"], ...
    ["-0.5;-49.5"; "-6.29;-43.71"], ...
    [strjoin(string(pole(s1)), ","); strjoin(string(pole(s1b)), ",")], ...
    ["6.03"; "0.5"], ...
    [info1.SettlingTime; info1b.SettlingTime], ...
    ["9.101"; "0.09"], ...
    [abs(1 - y1(end)); abs(1 - y1b(end))], ...
    VariableNames=["Tf"; "Est: P"; "Pôles"; "Est: ST"; "Settling time"; "Est: SE"; "Static error"] ...
));