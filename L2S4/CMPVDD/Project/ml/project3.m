stt = 0.05;
tFinal = 3;

s3 = zpk([], [0 -5], 20);
info1 = stepinfo(s3, "SettlingTimeThreshold", stt);
[y3, t3] = step(s3, tFinal);

s3b = feedback(tf(10, [1/25, 1/5, 1]), 1);
info2b = stepinfo(s3b, "SettlingTimeThreshold", stt);
[y3b, t3b] = step(s3b, tFinal);

sp = stepplot(s3, s3b, tFinal);

% Input plot
yl = yline(1, "-");
yl.Color = [255/255, 152/255, 0/255];

% Legend
title(sp, "Partie II - 2nd ordre instable");
subtitle(sp, "Système bouclé (Comparaison)");
xlabel(sp, "t")

legend(sp, "s3(t)", "s'3(t)", "e(t)")

% Characteritics
sp.InputVisible = "on";
sp.YLimits = [0,3];
sp.Characteristics.SettlingTime.Threshold = stt;
sp.Characteristics.SettlingTime.Visible = "on";
sp.Characteristics.PeakResponse.Visible = "on";
