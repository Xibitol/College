stt = 0.05;
tFinal = 20;

s1 = zpk([], [-1/2 -99/2], 250);
info1 = stepinfo(s1, "SettlingTimeThreshold", stt);
[y1, t1] = step(s1, tFinal);

s2 = tf(10, [1/25, 1/5, 1]);
info2 = stepinfo(s2, "SettlingTimeThreshold", stt);
[y2, t2] = step(s2, tFinal);

sp = stepplot(s1, s2, tFinal);

% Input plot
yl = yline(1, "-");
yl.Color = [255/255, 152/255, 0/255];

% Legend
title(sp, "Partie II - 2nd ordre");
subtitle(sp, "Système seul");
xlabel(sp, "t")

legend(sp, "s1(t)", "s2(t)", "e(t)")

t = text(info1.SettlingTime + 0.25, y1(end)/1.05 - 0.25, "Rapidité à moyen terme");
t.Color = [0 0.447 0.741];

t = text(info2.SettlingTime + 0.25, y2(end) + 0.25, "Rapidité à moyen terme");
t.Color = [0.851 0.325 0.098];
t = text(info2.PeakTime + 0.25, info2.Peak - 0.25, "Dépassement");
t.Color = [0.851 0.325 0.098];

% Characteritics
sp.InputVisible = "on";
sp.YLimits = [0,13];
sp.Characteristics.SettlingTime.Threshold = stt;
sp.Characteristics.SettlingTime.Visible = "on";
sp.Characteristics.PeakResponse.Visible = "on";

disp(table(...
    ["s1(t)"; "s2(t)"], ...
    ["-0.5;-49.5"; "-2.5-4.3301i;-2.5+4.3301i"], ...
    [strjoin(string(pole(s1)), ","); strjoin(string(pole(s2)), ",")], ...
    ["6.03"; "1.05"], ...
    [info1.SettlingTime; info2.SettlingTime], ...
    ["9.101"; "9"], ...
    [abs(1 - y1(end)); abs(1 - y2(end))], ...
    ["0"; "0.16"], ...
    [info1.Overshoot/100; info2.Overshoot/100], ...
    VariableNames=["Tf"; "Est: P"; "Pôles"; "Est: ST"; "Settling time"; "Est: SE"; "Static error"; "Est: OS"; "Overshoot"] ...
));