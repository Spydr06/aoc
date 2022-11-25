record Edge, num : Int32, dest : String

def visit(graph, node : String, acc = 0)
  graph[node].each do |edge|
    dest = edge.dest
    acc += edge.num + edge.num * visit(graph, dest)
  end
  return acc
end

def part2(input)
  baghash = Hash(String, Array(Edge)).new { |h, key| h[key] = [] of Edge }
  input.lines.map { |line|
    bag, contents = line.split("s contain ")
    contents.scan(/([0-9]+) (\w+ \w+ bag)/).each do |mdata|
      num, innerbag = mdata.captures
      baghash[bag] << Edge.new(num.to_i, innerbag) unless num.nil? || innerbag.nil?
    end
  }
  visit(baghash, "shiny gold bag")
end

input = ARGF.gets_to_end

p! part2(input)