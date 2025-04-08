stt = 0.05;
tFinal = 3;

s1b = feedback(zpk([], [-1/2 -99/2], 250), 1);
info1 = stepinfo(s1b, "SettlingTimeThreshold", stt);
[y1b, t1b] = step(s1b, tFinal);

s2b = feedback(tf(10, [1/25, 1/5, 1]), 1);
info2b = stepinfo(s2b, "SettlingTimeThreshold", stt);
[y2b, t2b] = step(s2b, tFinal);

sp = stepplot(s1b, s2b, tFinal);

% Input plot
yl = yline(1, "-");
yl.Color = [255/255, 152/255, 0/255];

% Legend
title(sp, "Partie II - 2nd ordre");
subtitle(sp, "Système M'1 et M'2 bouclé (Comparaison)");
xlabel(sp, "t")

legend(sp, "s'1(t)", "s'2(t)", "e(t)")

t = text(info2.SettlingTime + 0.25, y1b(end)/1.05 - 0.25, "Rapidité à moyen terme");
t.Color = [0 0.447 0.741];

t = text(info2b.SettlingTime + 0.25, y2b(end) - 0.25, "Rapidité à moyen terme");
t.Color = [0.851 0.325 0.098];
t = text(info2b.PeakTime + 0.25, info2b.Peak + 0.25, "Dépassement");
t.Color = [0.851 0.325 0.098];

% Characteritics
sp.InputVisible = "on";
sp.YLimits = [0,3];
sp.Characteristics.SettlingTime.Threshold = stt;
sp.Characteristics.SettlingTime.Visible = "on";
sp.Characteristics.PeakResponse.Visible = "on";
