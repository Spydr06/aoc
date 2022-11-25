using System;
using System.Linq;
using System.IO;

class Tile
{
    int id;
    char[][] data;
    public Tile(string id, char[][] data)
    {
        this.id = Int32.Parse(id);
        this.data = data;

        Console.WriteLine($"New Tile: {id}");
    }

    void rotate()
    {
        char[][] oldData = data;
        for (int i = 0; i < data.Length; ++i)
        {
            for (int j = 0; j < data.Length; ++j)
            {
                data[i][j] = oldData[data.Length - j - 1][i];
            }
        }
    }

    void flipX()
    {

    }

    void flipY()
    {
        
    }
}

Tile[][] makeTileMap(List<Tile> tiles)
{
    int size = (int)Math.Sqrt(tiles.Count());
    Tile[][] map = new Tile[size][];

    int c = 0;
    for (int i = 0; i < size; i++)
    {
        map[i] = new Tile[size];
        for (int j = 0; j < size; j++)
        {
            map[i][j] = tiles[c];
            c++;
        }
    }
    return map;
}

string[] input = File.ReadAllText("input.txt").Split(new string[] { "\r\n\r\n" }, StringSplitOptions.RemoveEmptyEntries);
List<Tile> tiles = new List<Tile>();

foreach (var input_tile in input)
{
    string id = input_tile.Substring(5, 4);
    string[] raw_data_lines = input_tile.Substring(12).Split(new string[] {"\r\n"}, StringSplitOptions.RemoveEmptyEntries);
    char[][] data = raw_data_lines.Select(item => item.ToArray()).ToArray();

    tiles.Add(new Tile(id, data));
}

Tile[][] image = makeTileMap(tiles);

