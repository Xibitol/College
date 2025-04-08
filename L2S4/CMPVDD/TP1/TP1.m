s1 = tf(1, [6, 1]);
s2 = tf(10, [2, 1]);

% Step
st = 0.05;
info1 = stepinfo(s1, "SettlingTimeThreshold", st);
info2 = stepinfo(s2, "SettlingTimeThreshold", st);

results = table(...
    {"s1(t)"              ; "s2(t)"}, ...
    [18                   ; 6], ...
    [info1.SettlingTime   ; info2.SettlingTime], ...
    [0                    ; -9], ...
    [1 - info1.SettlingMax; 1 - info2.SettlingMax], ...
    VariableNames={'Tf'; 'Est: ST'; 'Settling time'; 'Est2: SE'; 'Static error'} ...
);

disp("Step info:");
disp(results);

% Impulse
[y1, t1] = impulse(s1, 15);
[y2, t2] = impulse(s2, 15);

plot(t1, y1, t2, y2);
title("Impulse response");
legend("s1(t)", "s2(t)");
xlabel("t");