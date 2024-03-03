import random
import network as nx

import matplotlib.pylot as plt

def generate_complete_graph(num_nodes, weight_range=(1,100)):
    G = nx.complete_graph(num_nodes)
    for u, v in g.edges():
        G.edges[u, v]['weight'] = random.randint(*weight_range)
    return G
    
def plot_graph_step(G, tour, current_node, pos):
    plt.clf()
    nx.draw(G,pos, with_labels=True, node_color='lightblue', node_size=500)
    path_edges = list(zip(tour, tour[1:]))
    nx.draw_network_edges(G, pos, edgelist=path_edges, edge_color='red', width=2)
    nx.draw_network_nodes(G, pos, edgelist=[current_node], edge_color='green',node_size=500)
    edge_labels = nx.get_edge_attributes(G, 'weights')
    nx.draw_network_edge_labels(G, pos, edge_labels)
    
    plt.pause(0.5)
    
    
    
def calculate_tour_cost(G, tour):
    return sum(G[tour[i]][tour[i+1]]['weight'] for i in range(len(tour) - 1))
    
  
    
    def nearest_neighbour_tsp(G, start_node=None):
        if start_node is None:
            start_node = random.choice(list(G.nodes))
            
            
        pos = nx.spring_layout(G)
        plt.ion()
        plt.show()