input: starting solution
input: neighborhood operator, N
input: evaluation function, f
current<- s0
done<- false
while done = false do
  best_neighbor <-current
  for each s in N(current) do
    if f(s) < f(best_neighbor) then
        best_neighbor <- s
    end if
end for
if current = best_neighbor then
  done = true
else
  current = best_neighbor
 end if
end while
