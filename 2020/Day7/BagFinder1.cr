#count = 0
#for each bag:
#  if contains-shiny(bag) is true: count++
#
#function contains-shiny(bag):
#  for each sub-bag of bag:
#    if sub-bag = "shiny gold" or contains-shiny(sub-bag): return true
#  return false

record Edge, num : Int32, dest : String

def visit(graph, node : String, visited : Array(String))
  visited << node
  graph[node].each do |x|
    visit(graph, x, visited) unless x.in?(visited)
  end
  return visited
end

def part1(input)
  baghash = Hash(String, Array(String)).new { |h, key| h[key] = [] of String }
  input.lines.map { |line|
    bag, contents = line.split("s contain ")
    contents.scan(/\w+ \w+ bag/) do |match|
      if inner_bag = match[0]
        baghash[inner_bag] << bag
      end
    end
  }
  visited = [] of String
  visit(baghash, "shiny gold bag", visited)
  visited.size - 1
end

input = ARGF.gets_to_end

p! part1(input)