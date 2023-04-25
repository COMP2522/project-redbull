import pygame
import math
import json
import os
import tkinter as tk
from tkinter import filedialog

class GridPainter:
    def __init__(self, num_squares=36, screen_width=900, screen_height=900):
        # initialize Pygame
        pygame.init()
        self.root = tk.Tk()
        self.root.withdraw()  # hide the root window
        self.spawn = (10,10)
        # set up the window
        self.screen_width = screen_width
        self.screen_height = screen_height
        self.screen = pygame.display.set_mode((screen_width, screen_height), pygame.RESIZABLE)
        pygame.display.set_caption("Click to color squares")

        # set up the grid
        self.num_squares = num_squares
        self.square_size = math.floor(self.screen_width / self.num_squares)
        self.grid = [[0 for i in range(self.num_squares)] for j in range(self.num_squares)]

        self.brush_size = 1  # start with a default brush size of 1
        self.MAX_BRUSH_SIZE = 10  # set a maximum brush size

        # set up colors
        self.WHITE = (255, 255, 255)
        self.BLACK = (0, 0, 0)
        self.RED = (255, 0, 0)
        self.GREEN = (0, 255, 0)

        # set up clock
        self.clock = pygame.time.Clock()
        self.fps = 60
        self.previous_cell = None
        self.running = True        
    def draw_grid(self):
        # inside the draw_grid() function
        mouse_x, mouse_y = pygame.mouse.get_pos()
        
        current_cell_x = mouse_x // self.square_size
        current_cell_y = mouse_y // self.square_size
        self.screen.fill(self.WHITE)
        # print(self.grid)
        for x in range(self.num_squares):
            for y in range(self.num_squares):
                square_x = x * self.square_size
                square_y = y * self.square_size
                if self.grid[x][y] == 1:
                    # draw a black square
                    pygame.draw.rect(self.screen, self.BLACK, (square_x, square_y, self.square_size, self.square_size))
                    # print("wall")
                elif self.grid[x][y] == 2:
                    # draw a red square
                    pygame.draw.rect(self.screen, self.RED, (square_x, square_y, self.square_size, self.square_size))
                    # print("food")
                elif self.spawn == (x,y) or self.spawn == (x,y+1) or self.spawn == (x,y+2) or self.spawn == (x,y+3):
                    # draw a green square
                    pygame.draw.rect(self.screen, self.GREEN, (square_x, square_y, self.square_size, self.square_size))
                    # print("spawn")
                else:
                    # draw a white square
                    pygame.draw.rect(self.screen, self.WHITE, (square_x, square_y, self.square_size, self.square_size), 1)
                    pygame.draw.rect(self.screen, self.BLACK, (square_x, square_y, self.square_size, self.square_size), 1)  
                # highlight cells under the brush in grey
                if (x - self.brush_size <= current_cell_x) and (x + self.brush_size >= current_cell_x) and (y - self.brush_size <= current_cell_y) and (y + self.brush_size >= current_cell_y) and not self.grid[x][y]:
                    pygame.draw.rect(self.screen, (210, 210, 210), (square_x+self.square_size/10, square_y+self.square_size/10, self.square_size-self.square_size/5, self.square_size-self.square_size/5))
                if (x - self.brush_size <= current_cell_x) and (x + self.brush_size >= current_cell_x) and (y - self.brush_size <= current_cell_y) and (y + self.brush_size >= current_cell_y) and self.grid[x][y]:
                    pygame.draw.rect(self.screen, (190, 190, 190), (square_x+self.square_size/10, square_y+self.square_size/10, self.square_size-self.square_size/5, self.square_size-self.square_size/5))

        # update the display
        pygame.display.flip()
        # set the framerate
        self.clock.tick(self.fps)
    def paint(self,current_cell_x, current_cell_y):
        # update the state of the cell and surrounding cells within the brush size
            for x in range(current_cell_x-self.brush_size, current_cell_x+self.brush_size+1):
                for y in range(current_cell_y-self.brush_size, current_cell_y+self.brush_size+1):
                    if x >= 0 and x < self.num_squares and y >= 0 and y < self.num_squares:
                        self.grid[x][y] = 1
    def paintFood(self,current_cell_x, current_cell_y):
        # update the state of the cell and surrounding cells within the brush size
            for x in range(current_cell_x-self.brush_size, current_cell_x+self.brush_size+1):
                for y in range(current_cell_y-self.brush_size, current_cell_y+self.brush_size+1):
                    if x >= 0 and x < self.num_squares and y >= 0 and y < self.num_squares:
                        self.grid[x][y] = 2
    def erase(self,current_cell_x, current_cell_y):
        # update the state of the cell and surrounding cells within the brush size
        for x in range(current_cell_x-self.brush_size, current_cell_x+self.brush_size+1):
            for y in range(current_cell_y-self.brush_size, current_cell_y+self.brush_size+1):
                if x >= 0 and x < self.num_squares and y >= 0 and y < self.num_squares:
                    self.grid[x][y] = 0
    def fill(self,current_cell_x, current_cell_y):
        # fill adjacent cells with the current state of the clicked cell
        fill_cell = self.grid[current_cell_x][current_cell_y]
        stack = [(current_cell_x, current_cell_y)]
        visited = set()
        while stack:
            x, y = stack.pop()
            if (x, y) in visited:
                continue
            visited.add((x, y))
            if self.grid[x][y] != fill_cell:
                continue
            self.grid[x][y] = 1
            if x > 0:
                stack.append((x - 1, y))
            if x < self.num_squares - 1:
                stack.append((x + 1, y))
            if y > 0:
                stack.append((x, y - 1))
            if y < self.num_squares - 1:
                stack.append((x, y + 1))
    def eraseFill(self,current_cell_x, current_cell_y):
        # fill adjacent cells with the current state of the clicked cell
        fill_cell = self.grid[current_cell_x][current_cell_y]
        stack = [(current_cell_x, current_cell_y)]
        visited = set()
        while stack:
            x, y = stack.pop()
            if (x, y) in visited:
                continue
            visited.add((x, y))
            if self.grid[x][y] != fill_cell:
                continue
            self.grid[x][y] = 0
            if x > 0:
                stack.append((x - 1, y))
            if x < self.num_squares - 1:
                stack.append((x + 1, y))
            if y > 0:
                stack.append((x, y - 1))
            if y < self.num_squares - 1:
                stack.append((x, y + 1))
    def watchIO(self):
                # handle events
        for event in pygame.event.get():
            ctrl_pressed = pygame.key.get_pressed()[pygame.K_LCTRL] or pygame.key.get_pressed()[pygame.K_RCTRL]
            alt_pressed = pygame.key.get_pressed()[pygame.K_LALT] or pygame.key.get_pressed()[pygame.K_RALT]
            tab_pressed = pygame.key.get_pressed()[pygame.K_TAB]
            shift_pressed = pygame.key.get_pressed()[pygame.K_LSHIFT] or pygame.key.get_pressed()[pygame.K_RSHIFT]
            mouse_x, mouse_y = pygame.mouse.get_pos()
            current_cell_x = mouse_x // self.square_size
            current_cell_y = mouse_y // self.square_size
            if event.type == pygame.QUIT:
                self.running = False
            elif event.type == pygame.MOUSEMOTION:
                print(shift_pressed)
                if pygame.mouse.get_pressed()[0]: 
                    if ctrl_pressed and not alt_pressed:
                        self.erase(current_cell_x, current_cell_y)
                    elif alt_pressed and not ctrl_pressed:
                        self.fill(current_cell_x, current_cell_y)
                    elif ctrl_pressed and alt_pressed:
                        self.eraseFill(current_cell_x, current_cell_y)
                    elif tab_pressed:
                        self.paintFood(current_cell_x, current_cell_y)
                    elif shift_pressed:
                        self.moveSpawn(current_cell_x, current_cell_y)
                    else:
                        self.paint(current_cell_x, current_cell_y)
                    
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    self.save()
                elif event.key == pygame.K_ESCAPE:
                    root = tk.Tk()
                    root.withdraw()
                    file_path = filedialog.askopenfilename()
                    if file_path and os.path.isfile(file_path):
                        self.load(file_path)
            # inside the watchIO() function
            if event.type == pygame.MOUSEBUTTONDOWN and event.button == 4:
                # scroll up to increase the brush size
                self.brush_size = min(self.brush_size + 1, self.MAX_BRUSH_SIZE)
            elif event.type == pygame.MOUSEBUTTONDOWN and event.button == 5:
                # scroll down to decrease the brush size
                self.brush_size = max(self.brush_size - 1, 0)
            # print(brush_size)
            if pygame.mouse.get_pressed()[0]: 
                    if ctrl_pressed and not alt_pressed:
                        self.erase(current_cell_x, current_cell_y)
                    elif alt_pressed and not ctrl_pressed:
                        self.fill(current_cell_x, current_cell_y)
                    elif ctrl_pressed and alt_pressed:
                        self.eraseFill(current_cell_x, current_cell_y)
                    elif tab_pressed:
                        self.paintFood(current_cell_x, current_cell_y)
                    elif shift_pressed:
                        self.moveSpawn(current_cell_x, current_cell_y)
                    else:
                        self.paint(current_cell_x, current_cell_y)
    def clear_grid(self):
        self.grid = [[0 for i in range(self.num_squares)] for j in range(self.num_squares)]
    def save(self):
       # open a file dialog and get the selected filename
        filename = filedialog.asksaveasfilename(defaultextension=".json")
        if not filename:
            return

        # save the grid state to the selected file
        spawn = []
        spawn.append({"x": self.spawn[0], "y": self.spawn[1]})
        maze = []
        for i in range(self.num_squares):
            for j in range(self.num_squares):
                if self.grid[i][j] == 1:
                    maze.append({"x": j, "y": i})
        food = []
        for i in range(self.num_squares):
            for j in range(self.num_squares):
                if self.grid[i][j] == 2:
                    food.append({"x": j, "y": i})


        with open(filename, "w") as f:
            print(json.dumps({"maze": maze, "spawn":spawn, "food":food}, indent=4), file=f)
    def load(self, filename):
        # clear the grid
        self.clear_grid()
        with open(filename, "r") as f:
            data = json.load(f)
            maze = data["maze"]
            spawn = data["spawn"]
            food = data["food"]
            for cell in maze:
                x, y= cell["y"], cell["x"]  # swap x and y
                if x >= 0 and x < self.num_squares and y >= 0 and y < self.num_squares:
                    self.grid[x][y] = 1
            for cell in spawn:
                x, y = cell["x"], cell["y"]
                if x >= 0 and x < self.num_squares and y >= 0 and y < self.num_squares:
                    self.spawn = (x, y)
            for cell in food:
                x, y = cell["y"], cell["x"]
                if x >= 0 and x < self.num_squares and y >= 0 and y < self.num_squares:
                    self.grid[x][y] = 2
    def run(self):
        self.running = True
        while self.running:
            self.watchIO()
            self.draw_grid()
        
        pygame.quit()
    def moveSpawn(self, x, y):
        self.spawn = (x, y)
        print(self.spawn)
def main():
    # create the game object
    App = GridPainter()
    # start the game loop
    App.run()
if __name__ == "__main__":
   main()
