//python
import random

arr = [random.randint(-5, 5) for _ in range(5)]

print("Random Integers:", arr)

sum_of_squares = sum(x**2 for x in arr)

print("Sum =", sum_of_squares)
