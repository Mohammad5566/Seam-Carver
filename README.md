# Seam Carver

[Seam Carving](https://www.wikiwand.com/en/Seam_carving) is a content-aware image resizing technique where the image is reduced in size by one pixel of height or width at a time. A vertical seam in an image is a path of pixels connected from the top to the bottom with one pixel in each row. A horizontal seam is a path of pixels connected from the left to the right with one pixel in each column.  

Finding and removing a seam is done in three parts:

**Energy calculation:** The first step is to calculate the energy of each pixel, which is a measure of the importance of each pixel—the higher the energy, the less likely that the pixel will be included as part of a seam (as we’ll see in the next step). In this assignment, you will implement the dual gradient energy function, which is described below. Here is the dual gradient of the surfing image above:Seam Carving Josh EnergyA high-energy pixel corresponds to a pixel where there is a sudden change in color (such as the boundary between the sea and sky or the boundary between the surfer on the left and the ocean behind him). In the image above, pixels with higher energy values have whiter values. The seam-carving technique avoids removing such high-energy pixels.

**Seam Identification:** The next step is to find a vertical seam of minimum total energy. This is similar to the classic shortest path problem in an edge-weighted digraph except for the following:
 * The weights are on the vertices instead of the edges.
 * We want to find the shortest path from any of W pixels in the top row to any of the W pixels in the bottom row.
 * The digraph is acyclic, where there is a downward edge from pixel (x, y) to pixels (x − 1, y + 1), (x, y + 1), and (x + 1, y + 1), assuming that the coordinates are in the     prescribed range.

**Seam Removal:** The final step is remove from the image all of the pixels along the seam. The logic for this method has been implemented for you in the supplementary SeamRemover class, provided in SeamRemover.java.
