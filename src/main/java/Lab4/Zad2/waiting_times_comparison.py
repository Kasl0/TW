import pandas as pd
import matplotlib.pyplot as plt

# Read CSV file into a DataFrame
df = pd.read_csv('Documents\GitHub\TW\src\main\java\Lab4\Zad2\waiting_times.csv')

# Group DataFrame by 'function'
grouped = df.groupby('function')

# Extract subsets for 'get' and 'insert'
df_get = grouped.get_group('get')
df_insert = grouped.get_group('insert')

# Sort DataFrames by 'portion' column
df_get = df_get.sort_values(by='portion')
df_insert = df_insert.sort_values(by='portion')

# Plot for 'get' function
plt.plot(df_get['portion'], df_get['time'], label='get')

# Plot for 'insert' function
plt.plot(df_insert['portion'], df_insert['time'], label='insert')

# Add labels and title
plt.xlabel('Portion')
plt.ylabel('Time [ns]')
plt.title('Buffer waiting times')

# Add legend
plt.legend()

# Show the plot
plt.show()
