import math as m
import numpy as np

BLACK = "black"
WHITE = "white"
def simulateBallsGame(
	urns=[BLACK, WHITE],
	ballCount=10,
	urnQuantity={BLACK: 8, WHITE: 6}
):
	urnWeights = {urn: urnQuantity[urn]/ballCount for urn in urns}
	count = 1_000

	urnCounts = {urn: 0 for urn in urns}
	urnBallCounts = {urn: 0 for urn in urns}

	for _ in range(count):
		urn = rand.choice(urns)
		ball = rand.choices(urns, [
			urnWeights[urn] if urn == u else 1 - urnWeights[urn]
			for u in urns
		])

		urnCounts[urn] += 1
		if urn == ball[0]: urnBallCounts[urn] += 1

	# Result frequencies
	urnFreqs = {urn: c/count for urn, c in urnCounts.items()}
	urnBallFreqs = {
		urn: {
			ball: ( # Only two different balls possible
				urnBallCount if ball == urn else urnCounts[urn] - urnBallCount
			)/urnCounts[urn]
			for ball in urns
		} for urn, urnBallCount in urnBallCounts.items()
	}
	interFreqs = {
		urn: {
			ball: freq*urnFreqs[urn]
			for ball, freq in ballFreqs.items()
		}
		for urn, ballFreqs in urnBallFreqs.items()
	}
	ballFreqs = {
		ball: sum([interFreqs[urn][ball] for urn in urns]) for ball in urns
	}
	ballUrnFreqs = {
		ball: {
			urn: ballFreqs[ball]/freq
			for urn, ballFreqs in interFreqs.items()
		} for ball, freq in ballFreqs.items()
	}

	return {
		"urnFreqs": urnFreqs,
		"urnBallFreqs": urnBallFreqs,

		"interFreqs": interFreqs,
		"ballFreqs": ballFreqs,

		"ballUrnFreqs": ballUrnFreqs,

		"gain": 1*ballFreqs[BLACK] + (-1)*ballFreqs[WHITE]
	}

def binom(n, k):
	return m.factorial(n)/(m.factorial(k)*m.factorial(n - k))

def loibinomiale(n, p):
	return np.array([
		binom(n, k)*p**k*(1 - p)**(n - k)
		for k in range(n + 1)
	])

def loigeometrique(n, p):
	return np.array([
		(1 - p)**(k - 1)*p
		for k in range(n + 1)
	])

if __name__ == "__main__":
	import collections as cols
	import random as rand
	import scipy as sp
	import matplotlib.pyplot as plt

	fig = plt.figure(figsize=(10, 8))
	fig.canvas.manager.set_window_title("ADO - TP3")
	gridspec = fig.add_gridspec(2, 2)

	# PLOT rigged dice
	RIGGED_FACE = 3 - 1
	values = list(range(6))
	weights = [1/8 if v != RIGGED_FACE else 3/8 for v in values]
	expCounts = 20
	countPerExps = [10, 50, 100, 500, 1000]
	frequencies = {
		countPerExp: np.zeros((expCounts, len(values)))
			for countPerExp in countPerExps
	}

	for countPerExp, freqs in frequencies.items():
		for row in freqs:
			exp = cols.Counter(rand.choices(values, weights, k=countPerExp))
			for value, count in exp.items():
				row[value] = count/exp.total()

	axe = fig.add_subplot(gridspec[0, 0])
	axe.set_title("Rigged dice")
	axe.boxplot(
		np.concat(
			[freqs[:, [RIGGED_FACE]] for _, freqs in frequencies.items()],
			axis=1
		),
		tick_labels=[
			f"n={countPerExp}" for countPerExp, _ in frequencies.items()
		]
	)

	# PLOT balls and urns game
	# Simulation
	simulation = simulateBallsGame()

	for urn, freq in simulation["urnFreqs"].items():
		print(f"P({urn}Urn): {freq}")

	for urn, ballFreqs in simulation["urnBallFreqs"].items():
		for ball, freq in ballFreqs.items():
			print(f"P({ball}Ball|{urn}Urn) = {freq}")

	for urn, ballFreqs in simulation["interFreqs"].items():
		for ball, freq in ballFreqs.items():
			print(f"P({urn}Urn∩{ball}Ball) = {freq}")

	for ball, freq in simulation["ballFreqs"].items():
		print(f"P({ball}Ball) = {freq}")

	for ball, urnFreqs in simulation["ballUrnFreqs"].items():
		for urn, freq in urnFreqs.items():
			print(f"P({urn}Urn|{ball}Ball) = {freq}")

	print(f"E(Gain) = {simulation["gain"]}€")

	EXPERIMENTS = [(100, .5), (50, .75), (20, .25)]
	# PLOT Binomial law
	axe = fig.add_subplot(gridspec[0, 1])
	axe.set_title("Binomial law")
	for experiment in EXPERIMENTS:
		law = loibinomiale(*experiment)
		assert round(sum(law), 10) == 1, "Sum of successes of bionomial law ins't 1."

		axe.plot(range(experiment[0] + 1), law,
		   label=f"P(X=k) with n={experiment[0]} and p={experiment[1]}"
		)

	# PLOT Geometric law
	axe = fig.add_subplot(gridspec[1, 0])
	axe.set_title("Geometric law")
	axe.set_xlim((0, 20))
	axe.set_ylim((0, 1))
	axe.set_xticks(range(0, 20 + 1))
	for experiment in EXPERIMENTS:
		law = loigeometrique(*experiment)[1:]
		assert round(sum(law), 2) == 1, "Sum of successes of geometric law ins't 1."

		axe.plot(range(1, experiment[0] + 1), law,
		   label=f"P(X=k) with n={experiment[0]} and p={experiment[1]}"
		)

	# PLOT Normal law
	print(f"P(N > 1) = {sp.stats.norm.sf(1)}")
	print(f"P(N > 2) = {sp.stats.norm.sf(2)}")
	print(f"P(N ≤ -2) = {sp.stats.norm.cdf(-2)}")
	print(f"P(N ≤ -1) = {sp.stats.norm.cdf(-1)}")
	print("P(-1 < N ≤ 1) = P(|N| < 1) = {prob}".format(
		prob=sp.stats.norm.cdf(1) - sp.stats.norm.cdf(-1)
	))
	print("P(-2 < N ≤ 2) = P(|N| < 2) = {prob}".format(
		prob=sp.stats.norm.cdf(2) - sp.stats.norm.cdf(-2)
	))

	probabilities = [.90, .95, .99]	
	for prob in probabilities:
		x = sp.optimize.fsolve(
			lambda x: sp.special.erf(x/np.sqrt(2)) - prob,
			0
		)[0]
		print(sp.stats.norm.ppf((1 - prob)/2), sp.stats.norm.isf((1 - prob)/2))

		print("P(|N| < {x}) = {prob} = {calcProb}".format(
			x=x, prob=prob,
			calcProb=sp.stats.norm.cdf(x) - sp.stats.norm.cdf(-x)
		))

	# PLOT histogram of gain
	count = 1_000
	gains = []

	for _ in range(count):
		gains.append(simulateBallsGame()["gain"])

	axe = fig.add_subplot(gridspec[1, 1])
	axe.set_title("Average gains (Central limit theorem)")
	axe.hist(gains, 20)

	for axe in fig.axes:
		if len(axe.get_legend_handles_labels()[0]) > 0:
			axe.legend()
	fig.tight_layout()
	plt.show()