#!/usr/bin/ruby
require "pathname"

def help
    puts <<'DOC'
EXAMPLE USAGE:

./map-maker.rb \
  --name "Cool Map" \
  --plants "g=4,c=6" \
  --animals "R=40,F=60" \
  --size 25
DOC
    exit
end

def die *args
    STDERR.puts args.join(" ")
    exit 1
end

HELP = %r{(-|--|/)(h|help|\?)}
help if ARGV.any?{|x| x =~ HELP}

opts = {}
until ARGV.length == 0
    opt = ARGV.shift.sub(/^--?/, "").to_sym
    val = ARGV.shift

    opts[opt] = val
end

needed = [:name, :plants, :animals, :size]
help if needed.any?{|k| not opts.key? k}

COMMA  = /\s*,\s*/
EQUALS = /\s*=\s*/
SIZE = opts[:size].to_i
NAME = opts[:name]
PLANTS  = {}
ANIMALS = {}
POPULATION = SIZE ** 2

plants  = []
animals = []

tot = 0
opts[:plants].split(COMMA).each do |pair|
    k, v = pair.split(EQUALS)
    num = [0, v.to_f].max
    tot += num

    die "Learn to add" if tot > 100

    PLANTS[k] = num
end
PLANTS["_"] = 100 - tot.to_i

tot = 0
opts[:animals].split(COMMA).each do |pair|
    k, v = pair.split(EQUALS)
    num = [0, v.to_f].max
    tot += num

    die "Learn to add" if tot > 100

    ANIMALS[k] = num
end
ANIMALS["_"] = 100 - tot.to_i

PLANTS.each do |k, v|
    mult = v/100.0
    num = POPULATION * mult
    chars = [k] * num.to_i
    plants += chars
end

ANIMALS.each do |k, v|
    mult = v/100.0
    num = POPULATION * mult
    chars = [k] * num.to_i
    animals += chars
end

plants .shuffle!
animals.shuffle!

PREFIX = Pathname.new("resources").join("maps", NAME)
PREFIX.mkdir rescue nil

open PREFIX.join("terrain.dat"), "w" do |f|
    f.puts SIZE

    SIZE.times do
        SIZE.times do
            f.print(plants.pop || "_")
        end
        f.puts
    end
end

open PREFIX.join("animals.dat"), "w" do |f|
    f.puts SIZE

    SIZE.times do
        SIZE.times do
            f.print(animals.pop || "_")
        end
        f.puts
    end
end
