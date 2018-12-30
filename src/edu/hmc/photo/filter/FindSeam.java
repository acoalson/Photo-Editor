package edu.hmc.photo.filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FindSeam implements ImageFilter
{
    private static int[][] table;
    private static int[][] parent;
    private static int[] seam;

    @Override
    public BufferedImage filter(BufferedImage image)
    {        
        ImageFilter energy = new Energy();
        BufferedImage energyImage = energy.filter(image);
        
        int width = energyImage.getWidth();
        int height = energyImage.getHeight();
        
        //initializes table
        table = new int[width][height];
        parent = new int[width][height];
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int min = 0;
                
                // first row
                if (y == 0)
                {
                    //table[x][y] = energyImage.getRGB(x,0);
                    table[x][y] = getColor(x,0,energyImage);
                    
                    parent[x][y] = 0;
                }
                // first col
                else if (x == 0)
                {
                    table[x][y] = getColor(x,y,energyImage) + Math.min(table[x+1][y-1], table[x][y-1]);
                    
                    if (table[x][y] == getColor(x,y,energyImage) + table[x][y-1])
                        parent[x][y] = x;
                    else
                        parent[x][y] = x+1;
                }
                // last col
                else if (x == width-1)
                {
                    table[x][y] = getColor(x,y,energyImage) + Math.min(table[x-1][y-1], table[x][y-1]);
                    
                    if (table[x][y] == getColor(x,y,energyImage) + table[x][y-1])
                        parent[x][y] = x;
                    else
                        parent[x][y] = x-1;
                }
                else
                {
                    min = Math.min(table[x-1][y-1], table[x][y-1]);
                    min = Math.min(min, table[x+1][y-1]);
                    table[x][y] = getColor(x,y,energyImage) + min;
                    
                    if (table[x][y] == getColor(x,y,energyImage) + table[x][y-1])
                        parent[x][y] = x;
                    else if (table[x][y] == getColor(x,y,energyImage) + table[x-1][y-1])
                        parent[x][y] = x-1;
                    else
                        parent[x][y] = x+1;
                }
            }
        }
        
        //make the bottom row of seam
        seam = new int[height];
        int minBotSeam = table[0][height-1];
        int index = 0;
        for (int col = 0; col < width; col++)
        {
            if (table[col][height-1]<minBotSeam)
            {
                minBotSeam=table[col][height-1];
                index=col;
            }
        }
        seam[height-1] = index;
        
        //fill up the seam
        int x = parent[index][height-1];
        for (int row = height-2 ; row>=0 ; row--)
        {
            seam[row] = x;
            x = parent[x][row];
            
        }

        return image;
    }

    int[] getSeam()
    {
        return seam;
    }

    int[][] getTable()
    {
        return table;
    }

    int[][] getParent()
    {
        return parent;
    }
    
    int getColor(int x, int y, BufferedImage image)
    {
        Color color = new Color(image.getRGB(x, y));
        return color.getRed();
    }
    
    
    
}
